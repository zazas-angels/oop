package core;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
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

/**
 * Servlet to handle File upload request from Client
 * 
 * @author Javin Paul
 */
@WebServlet("/FileUploader")
public class FileUploader extends HttpServlet {
	private final String UPLOAD_DIRECTORY = "C:/Users/Guri/git/oop/WebContent";

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// process only if its multipart content
		String imageHTML = "";
		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				List<FileItem> multiparts = new ServletFileUpload(
						new DiskFileItemFactory()).parseRequest(request);

				for (FileItem item : multiparts) {
					if (!item.isFormField()) {
						File file = new File(item.getName());
						String name = file.getName();
						String pathImage = UPLOAD_DIRECTORY + File.separator
								+ name;
						if (isImage(file)) {
							System.out.println("image");
							item.write(new File(pathImage));
							imageHTML = "<img src=\""
									+ pathImage
									+ "\" style=\"width: 250px; height: 230px;\">";

						} else {
							System.out.println("notImage");
						}

						// File uploaded successfully
						// only one

					}
				}

			} catch (Exception ex) {
				// ignored
			}

		}
		if (!imageHTML.equals(""))
			response.getWriter().write(imageHTML);
		else {
			response.getWriter().write("Error Uploading");
		}
	}

	// checks if file is image : Server Side
	// This code is from:
	// http://stackoverflow.com/questions/18208359/how-to-check-if-the-file-is-an-image
	private boolean isImage(File file) {
		boolean result = true;
		try {
			Image image = ImageIO.read(file);
			if (image == null) {
				result = false;
			}
		} catch (IOException ex) {
			result = false;
		}

		return result;

	}

}