import React from 'react';
import "./LoginForm.css"
import {RightOutlined} from "@ant-design/icons";
import styled from "styled-components";
function LoginForm() {
    return (
        <div className="loginForm">
            <form action="">
                <input type="text" placeholder="아이디" className="loginIp"/>
                <br/>
                <input type="text" placeholder="비밀번호" className="loginIp"/>
                <br/>
                <div className="keepSearch">
                    <label for="keep" className="keepChkLb"><input type="checkbox" name="" id="keep" className="keepChk" />로그인 상태 유지</label>
                    <a href="findIdPw" className="idPwSearch">아이디 비밀번호 찾기 <RightOutlined /></a>
                </div>
                <input type="submit" value="로그인" className="loginBtn" />
            </form>
            <div className="socialLogin">
                <a href="" className="googleLogin"><img  src={require("../../assets/images/Login/googleIcon.png")} />구글 로그인</a><br/>
                <a href="" className="naverLogin"><img  src={require("../../assets/images/Login/naverIcon.webp")} />네이버 로그인</a><br/>
                <a href="" className="kakaoLogin"><img  src={require("../../assets/images/Login/kakaoIcon.png")} />카카오 로그인</a>
            </div>
            <div className="join">
                <a href="join" className="joinL">회원가입</a>
            </div>
        </div>
    );
}

export default LoginForm;