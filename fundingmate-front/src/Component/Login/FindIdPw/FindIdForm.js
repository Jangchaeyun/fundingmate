import React, {useState} from 'react';
import "./FindIdForm.css"
import axios from "axios";
import Swal from "sweetalert2";
import {useNavigate} from "react-router-dom";
import {DownOutlined, RightOutlined} from "@ant-design/icons";
function FindIdForm(props) {
    const [check, setCheck] = useState({randomNum:'',checkSMS:''})
    const [findId, setFindId] = useState({name:'',tel:'',email:''})
    const navigate = useNavigate();
    const submit = (e) => {
        e.preventDefault();
        console.log(findId.name);
        console.log(findId.tel);
        console.log(findId.email);
        axios.post('http://localhost:8080/findById', findId)
            .then(res=> {
                console.log(res);
                Swal.fire(res.data);
                navigate("/findIdResult",{state:res.data});
            })
            .catch(err=> {
                console.log(err);
                Swal.fire(err.data);
            })
    }
    const sendSMS = () => {
        alert("sendSMS");
        document.getElementById("checkSMS").focus();
        // axios.post("http://localhost:8080/send-one",null,{
        //     params:{
        //         tel:user.tel
        //     }
        // })
        //     .then(res => {
        //         console.log("front 확인");
        //         // check.randomNum = res.data;
        //     })
    }

    const checkSMS = () => {
        if (check.randomNum == check.checkSMS) {
            alert("휴대폰 인증이 정상적으로 완료되었습니다.");
        } else {
            alert("인증번호가 올바르지 않습니다.")
        }
    }
    const changeInput = (e) => {
        setFindId({...findId, [e.target.name]:e.target.value});
    }
    return (
        <div className="findIdform">
            <form onSubmit={submit}>
                <div className="findTitle">아이디를 찾을 방법을 선택해 주세요.</div>
                <div className="findwrap">
                    <div className="findwrapTab">
                        <div className="findwrap-text">회원정보에 등록한 휴대전화로 인증</div>
                        <p>본인확인용 휴대폰 번호와 입력한 휴대폰 번호가 동일 시 인증번호를 받을 수 있습니다.</p>
                        <DownOutlined className="findwrap-icon"/>
                    </div>
                    <input type="text" name="name" placeholder="이름" onChange={changeInput}/>
                    <input type="text" name="tel" placeholder="휴대폰 번호 입력 ('-) 제외)" onChange={changeInput}/>
                    <button type="button" className="authChkBtn" onClick={() => {sendSMS();alert("인증번호 발송 완료!!");}}>인증요청</button>
                </div>
                <div className="findwrap authblank" style={{display:"none"}}>
                    <input type="text" name="checkSMS" id="checkSMS" placeholder="인증번호" />
                    <button type="button" className="checkSmsBtn" onClick={() => {checkSMS();}}>인증번호 확인</button>
                </div>
                <div className="findwrap">
                    <div className="findwrapTab">
                        <div>본인확인 이메일로 인증</div>
                        <p>본인확인용 이메일 주소와 입력한 이메일 주소가 동일 시 인증번호를 받을 수 있습니다.</p>
                        <DownOutlined className="findwrap-icon"/>
                    </div>
                    <input type="text" name="name" placeholder="이름" onChange={changeInput}/>
                    <input type="email" name="email" placeholder="이메일" onChange={changeInput}/>
                    <button type="button" className="authChkBtn" onClick={() => {sendSMS();alert("인증번호 발송 완료!!");}}>인증요청</button>
                </div>
                <div className="findwrap authblank" style={{display:"none"}}>
                    <input type="text" name="checkSMS" id="checkSMS" placeholder="인증번호" />
                    <button type="button" className="checkSmsBtn" onClick={() => {checkSMS();}}>인증번호 확인</button>
                </div>
                <input type="submit" value="찾기" className="findSearch"/>
                <a href="login" className="loginFormBtn">로그인</a>
            </form>
        </div>
    );
}

export default FindIdForm;