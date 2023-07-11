import React from 'react';
import "./FindIdResultForm.css"
function FindIdResultForm(props) {
    return (
        <div className="findIdResultform">
            <div className="findTitle">아이디 찾기</div>
            <div className="findwrap">
                <p>고객님의 정보와 일치하는 아이디 목록입니다.</p>
                <div className="findIdInfo">
                    <div className="findIdResult">
                        <span className="findIdName">hong</span> - <span className="findIdJoinDate">2023.01.01</span>
                    </div>
                </div>
                <a href="login" className="formBtn">로그인</a>
                <a href="findPw" className="linkBtn">비밀번호 찾기</a>
            </div>
        </div>
    );
}

export default FindIdResultForm;