<script src="loginWithFacebook.js"></script>
<!--
Below we include the Login Button social plugin. This button uses
the JavaScript SDK to present a graphical Login button that triggers
the FB.login() function when clicked.
-->

<fb:login-button scope="public_profile,email" onlogin="checkLoginState();" login_text="
" class=" fb_iframe_widget" fb-xfbml-state="rendered"
                 fb-iframe-plugin-query="app_id=935732739811780&amp;container_width=0&amp;locale=en_US&amp;login_text=%0A&amp;scope=public_profile%2Cemail&amp;sdk=joey">
    <span
            style="vertical-align: bottom; width: 64px; height: 22px;">
    <iframe name="f5c050d5c" width="1000px"
            height="1000px" frameborder="0"
            allowtransparency="true"
            allowfullscreen="true" scrolling="no"
            title="fb:login_button Facebook Social Plugin"
            src="http://www.facebook.com/v2.3/plugins/login_button.php?app_id=935732739811780&amp;channel=http%3A%2F%2Fstatic.ak.facebook.com%2Fconnect%2Fxd_arbiter%2FQrU_tEEWym9.js%3Fversion%3D41%23cb%3Df2b1b4d754%26domain%3Dlocalhost%26origin%3Dhttp%253A%252F%252Flocalhost%253A8080%252Ff26fcdb53%26relation%3Dparent.parent&amp;container_width=0&amp;locale=en_US&amp;login_text=%0A&amp;scope=public_profile%2Cemail&amp;sdk=joey"
            style="border: none; visibility: visible; width: 64px; height: 22px;"
            class="">
    </iframe>
    </span>
</fb:login-button>