import React from 'react';
import Header from "../../Component/Header/Header";
import Footer from "../../Component/Footer/Footer";
import LoginForm from "../../Component/Login/LoginForm";

function Login() {
    return (
        <div className="login">
            <Header />
            <LoginForm />
            <Footer />
        </div>
    );
}

export default Login;