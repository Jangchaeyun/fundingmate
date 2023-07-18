import React from 'react';
import LoginForm from "../../Component/Login/LoginForm";
import CorFooter from "../../Component/Footer/CorFooter";
import LogoHeader from "../../Component/Header/LogoHeader";
import SocialLoginRedirectForm from "../../Component/Login/SocialLogin/SocialLoginRedirectForm";

function SocialLogin() {
    return (
        <div className="wrap">
            <LogoHeader />
            <SocialLoginRedirectForm />
            <CorFooter />
        </div>
    );
}

export default SocialLogin;