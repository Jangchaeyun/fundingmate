import React, {useState} from 'react';
import "./LoginForm.css"
import {RightOutlined} from "@ant-design/icons";
import styled from "styled-components";
import {useCookies} from "react-cookie";
import {useDispatch} from "react-redux";
import axios from "axios";
function LoginForm() {
    const [id, setId] = useState("");
    const [password, setPassword] = useState("");
    const [cookie, setCookie] = useCookies(['refreshToken']);
    const dispatch = useDispatch();
    const login = (e) => {
        e.preventDefault();
        axios.post("http://localhost:8080/login",null,
            {
                params:{
                    id:id,
                    password:password
                }
            })
            .then(res=> {
                dispatch({type:"NEWTOKEN", payload:res.data.accessToken})
                dispatch({type:"USERID", payload:res.data.userid})

                const expires = new Date();
                expires.setDate(expires.getDate()+1);
                setCookie('refreshToken',res.data.refreshToken, {
                    url:'/', expires
                })
            })
            .catch(err=> {
                console.log(err);
            })
    }
    return (
        <div className="loginForm">
            <form onSubmit={login}>
                <input type="text" name="id" value={id} onInput={(e)=>{setId(e.target.value)}} required placeholder="아이디" className="loginIp"/>
                <br/>
                <input type="text" name="password" value={password} onInput={(e)=>{setPassword(e.target.value)}} required placeholder="비밀번호" className="loginIp"/>
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