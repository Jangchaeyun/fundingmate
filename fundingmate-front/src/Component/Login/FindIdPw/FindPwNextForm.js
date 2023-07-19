import React from 'react';
import "./FindPwNextForm.css"
import {useLocation} from "react-router-dom";
function FindPwNextForm() {
    return (
        <div className="findPwNextForm">
            <form action="findPwResult">
                <input type="text" value={useLocation().state.id} style={{display:"none"}}/>
                <div className="findTitle">비밀번호 찾기</div>
                <div className="findwrap">
                    <div className="findwrap-text">회원정보에 등록한 휴대전화로 인증</div>
                    <input type="text" placeholder="이름"/>
                    <input type="text" placeholder="휴대폰 번호 입력 ('-) 제외)"/>
                    <button value="인증요청" className="authChk">인증요청</button>
                </div>
                <input type="text" placeholder="인증번호" style={{display:"none"}}/>
                <div className="findwrap">
                    <div>본인확인 이메일로 인증</div>
                    <p>본인확인용 이메일 주소와 입력한 이메일 주소가 동일 시 인증번호를 받을 수 있습니다.</p>
                    <input type="text" placeholder="이름"/>
                    <input type="email" placeholder="이메일"/>
                    <button value="인증요청" className="authChk">인증요청</button>
                </div>
                <input type="text" placeholder="인증번호" style={{display:"none"}}/>
                <input type="submit" value="찾기" className="findSearch"/>
                <a href="login" className="loginFormBtn">로그인</a>
            </form>
        </div>
    );
}

export default FindPwNextForm;