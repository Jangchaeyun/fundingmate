import React, { useState } from "react";
import "./FindIdResultForm.css";
import "./FindPwResultForm.css";
import { useLocation, useNavigate } from "react-router-dom";
import axios from "axios";
import Swal from "sweetalert2";
function FindPwResultForm(props) {
  const [user, setUser] = useState({
    password: "",
    passwordRe: ""
  });
  const { state } = useLocation();
  const navigate = useNavigate();
  const changeInput = (e) => {
    const { name, value } = e.target; // 변수를 만들어 이벤트가 발생했을때의 value를 넣어줬다
    const nextInputs = { ...user, [name]: value }; //스프레드 문법으로 기존의 객체를 복사한다.
    setUser(nextInputs);
  };
  function CheckPass(str) {
    //비밀번호 정규식
    let pwReg =
      /(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&.])[A-Za-z\d@$!%*#?&.]{8,16}$/;
    return pwReg.test(str);
  }
  const submit = (e) => {
    e.preventDefault();
    if (user.password == "") {
      alert("비밀번호를 입력해주세요!");
      document.getElementById("password").focus();
      return;
    } else if (CheckPass(user.password) === false) {
      alert(
        "비밀번호는 8~16자의 영문 대/소문자, 숫자, 특수문자를 조합하여 입력해주세요 !"
      );
      document.getElementById("password").focus();
      return;
    } else if (user.passwordRe == "") {
      alert("비밀번호 확인을 입력해주세요!");
      document.getElementById("passwordRe").focus();
      return;
    } else if (user.password !== user.passwordRe) {
      alert("비밀번호가 동일하지 않습니다!");
      document.getElementById("passwordRe").focus();
      return;
    } else {
      axios
        .post("http://localhost:8080/modifyPw", null, {
          params: {
            id: state.id,
            password: user.password
          }
        })
        .then((res) => {
          console.log(res);
          Swal.fire(res.data);
          navigate("/login");
        })
        .catch((err) => {
          Swal.fire(err.response.data);
        });
    }
  };
  return (
    <div className="findPwResultform">
      <div className="findTitle">비밀번호 찾기</div>
      <div className="findwrap">
        <form onSubmit={submit}>
          <div className="findwapTitle">비밀번호 재설정</div>
          <div className="findwrap">
            <input
              type="password"
              name="password"
              id="password"
              placeholder="새 비밀번호(8~16자의 영문 대/소문자, 숫자, 특수문자)"
              onChange={changeInput}
              autocomplete="off"
            />
            <input
              type="password"
              name="passwordRe"
              id="passwordRe"
              placeholder="비밀번호 확인"
              onChange={changeInput}
              autocomplete="off"
            />
          </div>
          <input type="submit" value="확인" className="formBtn" />
        </form>
      </div>
    </div>
  );
}

export default FindPwResultForm;
