import React, {useState} from 'react';
import "./FindIdResultForm.css"
import  "./FindPwResultForm.css"
import {useLocation, useNavigate} from "react-router-dom";
import axios from "axios";
import Swal from "sweetalert2";
function FindPwResultForm(props) {
    const [pw, setPw] = useState("");
    const { state } = useLocation();
    const navigate = useNavigate();
    const submit = (e) => {
        e.preventDefault();
        axios.post('http://localhost:8080/modifyPw',null,{
            params:{
                id:state.id,
                password:pw
            }
        })
            .then(res=> {
                console.log(res);
                Swal.fire(res.data);
                navigate("/login");
            })
            .catch(err=> {
                console.log(err);
                Swal.fire(err.data);
            })
    }
    return (
        <div className="findPwResultform">
            <div className="findTitle">비밀번호 찾기</div>
            <div className="findwrap">
                <form onSubmit={submit}>
                    <div className="findwapTitle">비밀번호 재설정</div>
                    <div className="findwrap">
                            <input type="password" placeholder="새 비밀번호" onInput={(e)=>{setPw(e.target.value)}}/>
                            <input type="password" placeholder="비밀번호 확인"/>
                    </div>
                    <input type="submit" value="확인" className="formBtn"/>
                </form>
            </div>
        </div>
    );
}

export default FindPwResultForm;