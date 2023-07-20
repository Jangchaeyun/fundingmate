import React, { useEffect, useState } from "react";
import "./LoginForm.css";
import { RightOutlined } from "@ant-design/icons";
import styled from "styled-components";
import { useCookies } from "react-cookie";
import { useDispatch } from "react-redux";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import Swal from "sweetalert2";
function LoginForm(props) {
  const [id, setId] = useState("");
  const [password, setPassword] = useState("");
  const [cookie, setCookie] = useCookies(["refreshToken"]);
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [rememberMe, setRememberMe] = useState(false);
  useEffect(() => {
    // 로컬 스토리지에서 토큰 가져오기
    const token = localStorage.getItem("token");

    // 토큰이 있으면 로그인 상태로 설정
    // if (token) {
    //   navigate("/");
    // }
    const urlParams = new URLSearchParams(window.location.search);
    const code = urlParams.get("code");

    if (code) {
      axios
        .get(`http://localhost:8080/login/kakao?code=${code}`)
        .then((res) => {
          const loginSuccess = res.data.loginSuccess;
          const user = res.data.user;
          if (loginSuccess) {
            // 로그인 성공 시 처리
            if (user.userid == null) {
              // 처음 로그인하는 사용자인 경우 회원가입 페이지로 이동

              navigate("/join", { state: res.data.user.snsLogin });
            } else {
              dispatch({ type: "NEWTOKEN", payload: res.data.accessToken });
              dispatch({ type: "USERID", payload: res.data.user.userid });

              const expires = new Date();
              expires.setDate(expires.getDate() + 1);
              setCookie("refreshToken", res.data.refreshToken, {
                url: "/",
                expires,
              });
              // 토큰을 로컬 스토리지에 저장
              storeTokenInLocalStorage(res.data.accessToken);
              // isLoggedIn 상태를 변경
              setIsLoggedIn(true);
              // 이미 회원가입된 사용자인 경우 메인 페이지로 이동
              navigate("/");
            }
          } else {
            // 로그인 실패 처리
          }
        })
        .catch((error) => {
          console.error("로그인 요청 실패:", error);
          // 에러 처리
        });
    }
  }, [isLoggedIn]);
  const storeTokenInLocalStorage = (token) => {
    localStorage.setItem("token", token);
  };
  const login = (e) => {
    e.preventDefault();
    axios
      .post("http://localhost:8080/login", null, {
        params: {
          id: id,
          password: password,
          // 'remember-me': true // Remember Me 파라미터를 설정하여 로그인 요청 보냄
        },
      })
      .then((res) => {
        console.log(res);
        dispatch({ type: "NEWTOKEN", payload: res.data.accessToken });
        dispatch({ type: "USERID", payload: res.data.userid });
        dispatch({ type: "ID", payload: res.data.id });

        const expires = new Date();
        expires.setDate(expires.getDate() + 1);
        setCookie("refreshToken", res.data.refreshToken, {
          url: "/",
          expires,
        });
        // 토큰을 로컬 스토리지에 저장
        storeTokenInLocalStorage(res.data.accessToken);
        // isLoggedIn 상태를 변경
        setIsLoggedIn(true);

        navigate("/");
      })
      .catch((err) => {
        Swal.fire(err.response.data.err);
        //console.log(err);
      });
  };
  const kakaoLogin = (e) => {
    window.location.href = `https://kauth.kakao.com/oauth/authorize?client_id=${process.env.REACT_APP_REST_API_KEY}&redirect_uri=${process.env.REACT_APP_REDIRECT_URL}&response_type=code`;
  };
  // const sociallogin = (e) => {
  //     alert("확인용");
  //     e.preventDefault();
  //     axios.get(`http://localhost:8080/oauth2/authorization/${process.env.REACT_APP_REST_API_KEY}?redirect_uri=${process.env.REACT_APP_REDIRECT_URL}`,null,
  //         {
  //             params:{
  //                 id:id,
  //                 password:password
  //             }
  //         })
  //         .then(res=> {
  //             alert("확인용1");
  //             console.log(res)
  //             dispatch({type:"NEWTOKEN", payload:res.data.accessToken})
  //             dispatch({type:"USERID", payload:res.data.userid})
  //
  //             const expires = new Date();
  //             expires.setDate(expires.getDate()+1);
  //             setCookie('refreshToken',res.data.refreshToken, {
  //                 url:'/', expires
  //             })
  //             document.location.href="/";
  //
  //         })
  //         .catch(err=> {
  //             alert("확인용2");
  //             console.log(err);
  //         })
  //
  // }
  return (
    <div className="loginForm">
      <form onSubmit={login}>
        <input
          type="text"
          name="id"
          id="id"
          value={id}
          onInput={(e) => {
            setId(e.target.value);
          }}
          required
          placeholder="아이디"
          className="loginIp"
          autocomplete="off"
        />
        <br />
        <input
          type="password"
          name="password"
          value={password}
          onInput={(e) => {
            setPassword(e.target.value);
          }}
          required
          placeholder="비밀번호"
          className="loginIp"
        />
        <br />
        <div className="keepSearch">
          <label for="keep" className="keepChkLb">
            <input
              type="checkbox"
              name=""
              id="keep"
              className="keepChk"
              checked={rememberMe}
              onChange={(e) => setRememberMe(e.target.checked)}
            />
            로그인 상태 유지
          </label>
          <a href="findIdPw" className="idPwSearch">
            아이디 비밀번호 찾기 <RightOutlined />
          </a>
        </div>
        <input type="submit" value="로그인" className="loginBtn" />
      </form>
      <div className="socialLogin">
        <button className="googleLogin">
          <img src={require("../../assets/images/Login/googleIcon.png")} />
          구글 로그인
        </button>
        <br />
        <button className="naverLogin">
          <img src={require("../../assets/images/Login/naverIcon.png")} />
          네이버 로그인
        </button>
        <br />
        <button className="kakaoLogin" onClick={kakaoLogin}>
          <img src={require("../../assets/images/Login/kakaoIcon.png")} />
          카카오 로그인
        </button>
      </div>
      <div className="join">
        <a href="join" className="joinL">
          회원가입
        </a>
      </div>
    </div>
  );
}

export default LoginForm;
