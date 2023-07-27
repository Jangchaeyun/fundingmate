import React, {useEffect, useState} from 'react';
import "./FindIdResultForm.css"
import {useLocation} from "react-router-dom";
function FindIdResultForm(props) {
    const { state } = useLocation();
    const [isHidden, setIsHidden] = useState(true);
    console.log(state);
    useEffect(() => {
        if(state.err == null) {
            setIsHidden(false);
        }
    }, [])
    return (
        <div className="findIdResultform">
            <div className="findTitle">아이디 찾기</div>
            <div className="findwrap">
                <p>고객님의 정보와 일치하는 아이디 목록입니다.</p>
                <div className="findIdInfo">
                    <div className="findIdResult">
                        {state.err}
                        {!isHidden &&
                            <div>
                                <div className="findIdName">아이디 : {state.userid}</div>
                                <div className="findIdJoinDate">가입일 : {state.joinDate}</div>
                            </div>
                        }
                    </div>
                </div>
                {state.err && <a href="/join" className="linkBtn">회원가입</a>}
                <a href="/login" className="formBtn">로그인</a>
                <a href="/findPw" className="linkBtn">비밀번호 찾기</a>
            </div>
        </div>
    );
}

export default FindIdResultForm;