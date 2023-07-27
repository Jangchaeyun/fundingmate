import React, {useState} from 'react';
import "./FindPwNextForm.css"
import {useLocation, useNavigate} from "react-router-dom";
import axios from "axios";
import Swal from "sweetalert2";
import {DownOutlined, UpOutlined} from "@ant-design/icons";
function FindPwNextForm() {
    const { state } = useLocation();
    const [check, setCheck] = useState({randomNum:'',checkSMS:''})
    const [emailCheck, setemailCheck] = useState({randomNum:'',checkSMS1:''})
    const [findId, setFindId] = useState({userid: state.id,name:'',tel:'',email:''})
    const navigate = useNavigate();
    const [isHidden, setIsHidden] = useState(true);
    const [isHidden1, setIsHidden1] = useState(true);
    const [istoggle, setIsistoggle] = useState(true);
    const [istoggle1, setIsistoggle1] = useState(false);
    const submit = (e) => {
        e.preventDefault();
        if(istoggle == true) {
            if(findId.name == ""){
                alert("이름을 입력해주세요!");
                document.getElementById("name").focus();
                return
            }else if(findId.tel == ""){
                alert("휴대폰 번호를 입력해주세요!");
                document.getElementById("tel").focus();
                return
            }else if(check.randomNum == "") {
                alert("휴대폰 인증요청 해주세요!");
                document.getElementById("checkSMS").focus();
                return
            }else if(check.checkSMS == "") {
                alert("인증번호를 입력해주세요!");
                document.getElementById("checkSMS").focus();
                return
            }else if(check.randomNum != check.checkSMS) {
                alert("인증번호확인 해주세요!");
                document.getElementById("checkSMS").focus();
                return
            }else {
                console.log(findId.name);
                console.log(findId.tel);
                console.log(findId.email);
                axios.post('http://localhost:8080/findByPw', findId)
                    .then(res=> {
                        console.log(res);
                        navigate("/findPwResult",{state:res.data});
                    })
                    .catch(err=> {
                        console.log(err);
                        Swal.fire("등록된 정보가 없습니다.");
                        navigate("/findPw");
                    })
            }

        }else if(istoggle1 == true){
            if(findId.name == ""){
                alert("이름을 입력해주세요!");
                document.getElementById("name").focus();
                return
            }else if(findId.email == ""){
                alert("이메일를 입력해주세요!");
                document.getElementById("email").focus();
                return
            }else if(emailCheck.randomNum == "") {
                alert("휴대폰 인증요청 해주세요!");
                document.getElementById("checkSMS1").focus();
                return
            }else if(emailCheck.checkSMS1 == "") {
                alert("인증번호를 입력해주세요!");
                document.getElementById("checkSMS1").focus();
                return
            }else if(emailCheck.randomNum != emailCheck.checkSMS1) {
                alert("인증번호확인 해주세요!");
                document.getElementById("checkSMS1").focus();
                return
            }else {
                console.log(findId.name);
                console.log(findId.tel);
                console.log(findId.email);
                axios.post('http://localhost:8080/findByPw', findId)
                    .then(res=> {
                        console.log(res);
                        navigate("/findPwResult",{state:res.data});
                    })
                    .catch(err=> {
                        console.log(err);
                        Swal.fire("등록된 아이디가 없습니다 ");
                        navigate("/findPw");
                    })
            }
        }else {
            alert("찾을 방법을 선택해 주세요.");
        }
    }
    const changeChkInput = (e) => {
        setCheck({ ...check, [e.target.name]: e.target.value });
    };
    const changeChkInput1 = (e) => {
        setemailCheck({ ...emailCheck, [e.target.name]: e.target.value });
    };
    const changeInput = (e) => {
        setFindId({...findId, [e.target.name]:e.target.value});
    }
    const sendSMS = (e) => {
        alert("sendSMS");
        axios.post("http://localhost:8080/send-one",null,{
            params:{
                tel:findId.tel
            }
        })
            .then(res => {
                console.log("front 확인");
                console.log(res.data);

                check.randomNum = res.data;
            })
        setIsHidden(false);
    }

    const checkSMS = () => {
        if (check.randomNum == check.checkSMS) {
            alert("휴대폰 인증이 정상적으로 완료되었습니다.");
        } else {
            alert("인증번호가 올바르지 않습니다.")
        }
    }
    const sendEmailSMS = (e) => {
        alert("sendEmailSMS");
        alert(findId.email);
        axios.post("http://localhost:8080/send-mail",null,{
            params:{
                email:findId.email
            }
        })
            .then(res => {
                console.log("front 확인");
                console.log(res.data);
                emailCheck.randomNum = res.data;
            })
        setIsHidden1(false);
        // 버튼을 클릭하여 숨겨진 요소가 보여질 때 자동으로 입력란에 포커스를 줌
    }

    const checkEmailSMS = () => {
        console.log(emailCheck.randomNum);
        console.log(emailCheck.checkSMS1);
        if (emailCheck.randomNum == emailCheck.checkSMS1) {
            alert("휴대폰 인증이 정상적으로 완료되었습니다.");
        } else {
            alert("인증번호가 올바르지 않습니다.")
        }
    }
    const handleButtonClick = () => {
        if(istoggle1 == true) {
            setIsistoggle1(!istoggle1);
        }
        if(istoggle == true) {
            setIsHidden(true);
        }
        setIsistoggle(!istoggle);
    };
    const handleButtonClick1 = () => {
        if(istoggle == true) {
            setIsistoggle(!istoggle);
        }
        if(istoggle1 == true) {
            setIsHidden1(true);
        }
        setIsistoggle1(!istoggle1);
    };
    return (
        <div className="findPwNextForm">
            <form onSubmit={submit}>
                <div className="findPwNextFormWrap">
                    <div className="findTitle">비밀번호를 찾을 방법을 선택해 주세요.</div>
                    <div className="findwrap">
                        <div className="findwrapTab" onClick={handleButtonClick }>
                            <div className="findwrap-text">회원정보에 등록한 휴대전화로 인증</div>
                            <p>본인확인용 휴대폰 번호와 입력한 휴대폰 번호가 동일 시 인증번호를 받을 수 있습니다.</p>
                            {!istoggle &&<DownOutlined className="findwrap-icon"/>}
                            {istoggle &&<UpOutlined className="findwrap-icon"/>}
                        </div>
                        {istoggle &&
                            <div>
                                <input type="text" name="name" id="name" placeholder="이름" onChange={changeInput}/>
                                <input type="text" name="tel" id="tel" placeholder="휴대폰 번호 입력 ('-) 제외)" onChange={changeInput}/>
                                <button type="button" className="authChkBtn" onClick={() => {sendSMS();alert("인증번호 발송 완료!!");}}>인증요청</button>
                            </div>
                        }
                        {!isHidden &&
                            <div className="findwrap authblank">
                                <input  type="text" name="checkSMS" id="checkSMS" placeholder="인증번호" onChange={changeChkInput}/>
                                <button type="button" className="checkSmsBtn" onClick={() => {checkSMS();}}>인증번호 확인</button>
                            </div>
                        }
                    </div>
                    <div className="findwrap">
                        <div className="findwrapTab" onClick={handleButtonClick1 }>
                            <div>본인확인 이메일로 인증</div>
                            <p>본인확인용 이메일 주소와 입력한 이메일 주소가 동일 시 인증번호를 받을 수 있습니다.</p>
                            {!istoggle1 &&<DownOutlined className="findwrap-icon"/>}
                            {istoggle1 &&<UpOutlined className="findwrap-icon"/>}
                        </div>
                        {istoggle1 &&
                            <div>
                                <input type="text" name="name" placeholder="이름" onChange={changeInput}/>
                                <input type="email" name="email" placeholder="이메일" onChange={changeInput}/>
                                <button type="button" className="authChkBtn" onClick={() => {sendEmailSMS();alert("인증번호 발송 완료!!");}}>인증요청</button>
                            </div>
                        }
                        {!isHidden1 &&
                            <div className="findwrap authblank">
                                <input type="text" name="checkSMS1" id="checkSMS1" placeholder="인증번호" onChange={changeChkInput1}/>
                                <button type="button" className="checkSmsBtn" onClick={() => {checkEmailSMS();}}>인증번호 확인</button>
                            </div>
                        }
                    </div>
                    <input type="submit" value="찾기" className="findSearch"/>
                    <a href="login" className="loginFormBtn">로그인</a>
                </div>
            </form>
        </div>
    );
}

export default FindPwNextForm;