<form id="mv_auth" name="mv_auth" action="log_in.php" method="POST" class="opened" style="display: block;">
    <div class="mv_auth_relative">
        <div class="auth_arrow"></div>
        <div class="mv_auth_input_holder left">
            <label class="bpgArial" for="g_user">ელ–ფოსტა</label>
            <input type="text" name="g_user" id="g_user" tabindex="1" class="mv_text_input">
        </div>

        <div class="mv_auth_input_holder">
            <label class="bpgArial" for="g_pass">
                პაროლი <a href="/?act=question">პაროლის აღდგენა</a>
            </label>
            <input type="password" name="g_pass" tabindex="2" id="g_pass" class="mv_text_input">
        </div>

        <div class="mv_auth_submit_holder">
            <input type="submit" name="Submit2" class="mv_login_button bpgNino" tabindex="4" value="საიტზე შესვლა">
            <label class="mv_remember_me bpgArial">
                <input type="checkbox" name="rememberMe" tabindex="3" value="true" checked="checked"
                       class="mv_remember_me_check"> დამიმახსოვრე </label>

            <div class="clear"></div>
        </div>

        <div class="clear"></div>
        <a href="/?CI=1&amp;ci_c=fb_login" tabindex="5" class="mv_fb_connect"></a>

        <script src="https://www.emoney.ge/js/econnect/v1/lib.js" type="text/javascript"></script>
        <script src="https://www.emoney.ge/js/econnect/v1/cl.js" type="text/javascript"></script>
        <script>
            $(document).ready(function () {


                $('.mv_emoney_connect').click(function (event) {
                    event.preventDefault();
                    client.showWidget({
                        id: "emoney_iframe",
                        widget_src: "https://www.emoney.ge/index.php/widget/login_common?lang=ka&protocol=http&domain=www.myvideo.ge&external_registration=0"
                    });
                    $('#mv_userblock #mv_auth').hide().removeClass('opened');
                    $('.piro_overlay').show();
                    $('#emoney_iframe').css('z-index', '999999999');
                });

                client.on('ping', function (a) {
                    client.callOut('logout');
                });

                client.on('close', function (a) {
                    $('.piro_overlay').hide();
                });

                client.on('load', function () {
                    console.log('loaded');
                });

                client.on('login', function (token) {
                    window.location.href = "http://www.myvideo.ge/?CI=1&ci_c=emoney&token=" + token;
                    console.log('Emoney Auth Token: ' + token);
                });

            });
        </script>
        <a class="mv_emoney_connect" tabindex="6"></a>

    </div>
</form>


