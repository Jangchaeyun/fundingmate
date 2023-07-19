import React, {useState} from 'react';
import "./FindPwForm.css"
import {Link} from "react-router-dom";
function FindPwForm(props) {
    const [id , setId] = useState('');

    return (
        <div className="findPwForm">
            <div className="findTitle">비밀번호 찾기</div>
            <div className="findwrap">
                <p>찾고자 하는 아이디 입력</p>
                <input type="text" name="id" placeholder="아이디" onInput={(e)=>{setId(e.target.value)}}/>
            </div>
            <Link to="/findPwNext" state={{ id: id }} className="linkBtn">다음</Link>
            <div className="findIdQ">
                <span className="findIdQText">아이디가 기억나지 않는다면? </span>
                <a href="findId" className="findIdSearch">아이디 찾기</a>
            </div>
        </div>
    );
}

export default FindPwForm;