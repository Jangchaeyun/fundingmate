import React from 'react';
import LoginForm from "../../Component/Login/LoginForm";
import CorFooter from "../../Component/Footer/CorFooter";
import LogoHeader from "../../Component/Header/LogoHeader";

function Login() {
    return (
        <div className="wrap">
            <LogoHeader />
            <LoginForm />
            <CorFooter />
        </div>
    );
}

export default Login;