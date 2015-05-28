<script>
    function statusChangeCallback(response) {
        console.log('statusChangeCallback');
        console.log(response);
        // The response object is returned with a status field that lets the
        // app know the current login status of the person.
        // Full docs on the response object can be found in the documentation
        // for FB.getLoginStatus().
        if (response.status === 'connected') {
            // Logged into your app and Facebook.
            testAPI();
        } else if (response.status === 'not_authorized') {
            // The person is logged into Facebook, but not your app.
            document.getElementById('status').innerHTML = 'Please log ' +
                    'into this app.';
        } else {
            // The person is not logged into Facebook, so we're not sure if
            // they are logged into this app or not.
            document.getElementById('status').innerHTML = 'Please log ' +
                    'into Facebook.';
        }
    }

    // This function is called when someone finishes with the Login
    // Button.  See the onlogin handler attached to it in the sample
    // code below.
    function checkLoginState() {
        FB.getLoginStatus(function (response) {
            statusChangeCallback(response);
        });
    }

    window.fbAsyncInit = function () {
        FB.init({
            appId: '935732739811780',
            xfbml: true,
            version: 'v2.3'
        });
    };


    (function (d, s, id) {
        var js, fjs = d.getElementsByTagName(s)[0];
        if (d.getElementById(id)) {
            return;
        }
        js = d.createElement(s);
        js.id = id;
        js.src = "//connect.facebook.net/en_US/sdk.js";
        fjs.parentNode.insertBefore(js, fjs);
    }(document, 'script', 'facebook-jssdk'));

    // Here we run a very simple test of the Graph API after login is
    // successful.  See statusChangeCallback() for when this call is made.
    function testAPI() {
        console.log('Welcome!  Fetching your information.... ');
        FB.api('/me', function (response) {
            console.log('Successful login for: ' + response.name);
            document.getElementById('status').innerHTML =
                    'Thanks for logging in, ' + response.name + '!';
        });
    }
</script>


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
            class=""></iframe></span>
</fb:login-button>