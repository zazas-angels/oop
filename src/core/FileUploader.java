package core;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/**
 * Servlet to handle File upload request from Client
 * 
 * @author Javin Paul
 */
@WebServlet("/FileUploader")
public class FileUploader extends HttpServlet {
	private final String UPLOAD_DIRECTORY = "C:/zaza";

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// process only if its multipart content
		//response.setContentType("image/jpeg");
		String imageHTML = "";
		File f = null;
		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				List<FileItem> multiparts = new ServletFileUpload(
						new DiskFileItemFactory()).parseRequest(request);

				for (FileItem item : multiparts) {
					if (!item.isFormField()) {
						// System.out.println(item.getName());
						File file = new File(item.getName());
						String name = file.getName();
						// System.out.println("name : "+name);
						String pathImage = UPLOAD_DIRECTORY + File.separator
								+ name;
						// System.out.println(pathImage);
						if (isImage(name)) {
							System.out.println("image");
							f = new File(pathImage);
							item.write(f);
							
							imageHTML = "<img src=\""
									+ "C:\\zaza\\"
									+ name
									+ "\" style=\"width: 100%; height: 100%;\">";

						} else {
							System.out.println("notImage");
							f = new File(pathImage);
							item.write(f);
						}

						// File uploaded successfully
						// only one

					}
				}

			} catch (Exception ex) {
				// ignored
			}

		}
		if (!imageHTML.equals("")) {
			BufferedImage image = ImageIO.read(f);
		     ByteArrayOutputStream baos = new ByteArrayOutputStream();
		     ImageIO.write(image, "png", baos);
		     String encodedImage = Base64.encode(baos.toByteArray());
			//BufferedImage bi = ImageIO.read(f);
			//OutputStream out = response.getOutputStream();
			//ImageIO.write(bi, "jpg", out);
			response.getWriter().print(encodedImage);
			//out.close();
		} else {
			// response.getWriter().write("Error Uploading");
		}
		System.out.println("morcha ha");
	}

	// checks if file is image : Server Side
	// This code is from:
	// http://stackoverflow.com/questions/18208359/how-to-check-if-the-file-is-an-image
	private boolean isImage(String name) {
		int index = name.length() - 2;// last index can't be .
		System.out.println("name" + name + "index " + index);

		while (index > 0) {
			System.out.println(index);
			if (name.charAt(index) == '.') {
				break;
			}
			index--;
		}
		if (index <= 0)
			return false;

		String extension = name.substring(index + 1).toLowerCase();
		System.out.println(extension);
		return extension.equals("png") || extension.equals("jpg")
				|| extension.equals("jpeg") || extension.equals("gif");

	}

}