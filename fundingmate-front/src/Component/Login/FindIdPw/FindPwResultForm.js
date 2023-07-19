import React from 'react';
import "./FindIdResultForm.css"
import  "./FindPwResultForm.css"
function FindPwResultForm(props) {
    return (
        <div className="findPwResultform">
            <div className="findTitle">비밀번호 찾기</div>
            <div className="findwrap">
                <form action="login">
                    <div className="findwapTitle">비밀번호 재설정</div>
                    <div className="findwrap">
                            <input type="password" placeholder="새 비밀번호"/>
                            <input type="password" placeholder="비밀번호 확인"/>
                    </div>
                    <input type="submit" value="확인" className="formBtn"/>
                </form>
            </div>
        </div>
    );
}

export default FindPwResultForm;