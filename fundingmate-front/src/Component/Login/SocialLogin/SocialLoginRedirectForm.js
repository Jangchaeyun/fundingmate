// // 리다이렉트될 화면
// import "./SocialLoginRedirectForm.css"
// import {useNavigate} from "react-router-dom";
// import {useEffect} from "react";
// import axios from "axios";
// import { Oval } from 'react-loader-spinner';
// const SocialLoginRedirectForm = (props) => {
//     const navigate = useNavigate();
//     const code = new URL(window.location.href).searchParams.get("code");
// //인가코드 백으로 보내는 코드
//     useEffect(() => {
//         const kakaoLogin = () => {
//             axios
//                 .get(`http://localhost:8080/login/kakao?code=${code}`)
//                 .then((res) => {
//                     console.log("성공");
//                     if (res.data.loginSuccess) {
//                         console.log("성공");
//                         // 계속 쓸 정보들( ex: 이름) 등은 localStorage에 저장해두자
//                         // localStorage.setItem("name", res.data.account.kakaoName);
//                         // 로그인이 성공하면 이동할 페이지
//                         navigate("/");
//                     } else {
//                         console.log("로그인 실패");
//                         // 로그인 실패 처리
//                     }
//                 })
//                 .catch((err) => {
//                     console.log("요청 실패");
//                     console.log(err);
//                 });
//         };
//         kakaoLogin();
//     }, [code]);
//
//     return (
//         <div className="socialLoginRedirectform">
//             <div>
//                 <div>잠시만 기다려 주세요! 로그인 중입니다.</div>
//                 <Oval height="80" width="80" radius="9" color="#00Bfff" ariaLabel="three-dots-loading" wrapperStyle wrapperClass />
//             </div>
//         </div>
//     );
// };
//
// export default SocialLoginRedirectForm;