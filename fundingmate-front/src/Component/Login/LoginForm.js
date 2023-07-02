import React from 'react';
import "./LoginForm.css"
import background from "../../assets/images/Login/Check.png"
function LoginForm() {
    return (
        <div className="loginForm">
            <div className="l-logo">LOGO</div>
            <form action="">
                <input type="text" placeholder="아이디" className="loginIp"/>
                <br/>
                <input type="text" placeholder="비밀번호" className="loginIp"/>
                <br/>
                <div className="keepSearch">
                    <span className="">
                        <input type="checkbox" name="" id="keep" className="keepChk" style={{backgroundImage:`url(${process.env.PUBLIC_URL + '/Check.png'})`}}/>
                        <label for="keep" className="">로그인 상태 유지</label>
                    </span>
                    <span className="idPwSearch">
                        <a href="">아이디</a>|
                        <a href="">비밀번호</a>
                        찾기
                    </span>
                </div>
                <input type="submit" value="로그인" className="loginBtn" />
            </form>
            <div className="socialLogin">
                <a href="" className="googleLogin">구글 로그인</a><br/>
                <a href="" className="naverLogin">네이버 로그인</a><br/>
                <a href="" className="kakaoLogin">카카오 로그인</a>
            </div>
            <div>
                <a href="">회원가입</a>
            </div>
        </div>
    );
}

export default LoginForm;