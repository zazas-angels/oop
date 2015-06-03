<!DOCTYPE html>
<html>
<head>
</head>
<body>
<input type="button" value="google+" onclick="login()">

<div id="profile"></div>
<script type="text/javascript">

    function logout() {
        gapi.auth.signOut();
        location.reload();
    }

    function login() {
        var myParams = {
            'clientid': '223066845585-k9jd80etd8sfgnia2e2ak4plt3qrk8in.apps.googleusercontent.com',
            'cookiepolicy': 'single_host_origin',
            'callback': 'loginCallback',
            'scope': 'https://www.googleapis.com/auth/plus.login https://www.googleapis.com/auth/plus.profile.emails.read'
        };
        gapi.auth.signIn(myParams);
    }

    function loginCallback(result) {
        if (result['status']['signed_in']) {
            var request = gapi.client.plus.people.get(
                    {
                        'userId': 'me'
                    });
            request.execute(function (resp) {
            });

        }

    }
    function onLoadCallback() {
        gapi.client.setApiKey('AIzaSyDACY9wkl5G608de2wVqtzECDjYFBG-AtI');
        gapi.client.load('plus', 'v1', function () {
        });
    }

</script>

<script type="text/javascript">
    (function () {
        var po = document.createElement('script');
        po.type = 'text/javascript';
        po.async = true;
        po.src = 'https://apis.google.com/js/client.js?onload=onLoadCallback';
        var s = document.getElementsByTagName('script')[0];
        s.parentNode.insertBefore(po, s);
    })();
</script>

</body>
</html>