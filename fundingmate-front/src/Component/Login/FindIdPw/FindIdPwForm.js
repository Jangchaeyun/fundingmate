import React from 'react';
import "./FindIdPwForm.css"
import {RightOutlined} from "@ant-design/icons";

function FindIdPwForm(props) {
    return (
        <div className="findIdPwForm">
            <div className="findIdPwtitle">아이디 비밀번호 찾기</div>
            <div className="findIdPwsearch">
                <a href="findId">아이디를 찾고 싶습니댜.<RightOutlined className="linkarrow"/></a><br/>
                <a href="findPw">비밀번호를 찾고 싶습니댜.<RightOutlined className="linkarrow"/></a>
            </div>
            <a href="/" className="mainL">메인페이지</a>
        </div>
    );
}

export default FindIdPwForm;