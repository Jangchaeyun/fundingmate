// components/Layout/Header/Header.tsx
import "./Header.css";
import {
  BellOutlined,
  LogoutOutlined,
  SearchOutlined
} from "@ant-design/icons";
import { useDispatch, useSelector } from "react-redux";
import { Link, useLocation, useNavigate } from "react-router-dom";
import { useState } from "react";
import axios from "axios";
function Header(props) {
  const token = useSelector((state) => state.Authorization);
  const userid = useSelector((state) => state.UserId);
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const [keyword, setKeyword] = useState("");
  const [reward, setReward] = useState([]);
  const logout = () => {
    dispatch({ type: "NEWTOKEN", payload: "" });
    dispatch({ type: "USERID", payload: "" });
    // 로컬 스토리지에서 토큰 제거
    localStorage.removeItem("token");

    // isLoggedIn 상태 변경
    setIsLoggedIn(false);

    document.location.href = "/login";
  };
  const searchSubmit = (e) => {
    if (keyword.trim() === "") {
      e.preventDefault();
      document.getElementById("keyword").value = "";
      document.getElementById("keyword").focus();
      return;
    } else {
      navigate(`/search/${keyword}`);
    }
    // axios.get(`http://localhost:8080/search?word=${keyword}`)
    //     .then(res => {
    //         console.log("성공");
    //         console.log(res);
    //         const resData = res.data;
    //         const addData = resData.map(item => {
    //             const projDateEnd = new Date(item.projDateEnd);
    //             const today = new Date();
    //             const differenceInTime = Math.abs(projDateEnd.getTime() - today.getTime());
    //             const differenceInDays = Math.ceil(differenceInTime / (1000 * 3600 * 24)-1);
    //             const formattedAmount = item.projTargetAmount.toLocaleString(); // 기본 로케일과 기본 숫자 포맷 사용
    //             console.log(differenceInDays);
    //             // item 객체와 차이를 추가하여 새로운 객체를 만듦
    //             return { ...item, differenceInDays, formattedAmount };
    //         });
    //         setReward(addData);
    //         let list = res.data.list;
    //         setReward([...list])
    //     })
    //     .catch(err => {
    //         console.log(err);
    //     })
    // navigate(`/search/${keyword}`);
  };
  return (
    <header className="header">
      <div className="header-wrap">
        <div className="hTop">
          <span className="hlogo">
            <a href="/" className="hlogoL logo">
              FundingMate
            </a>
          </span>
          <span className="hTopMenu">
            {(token == "" || token == undefined) && (
              <>
                <a href="/login">로그인</a>
                <a href="/join">회원가입</a>
              </>
            )}

            {/*<b className="userName">{userid}</b>*/}
            {userid != "" && (
              <>
                <a href="/myPage" className="myPageLink">
                  <img
                    src={require(`../../assets/images/defaultImg.jpg`)}
                    alt=""
                    className="myPageImg"
                  />
                </a>
                {/*<a href="#" className="noti">*/}
                {/*    <BellOutlined className="notiIcon" style={{ fontSize: "28px" }}/>*/}
                {/*</a>*/}
                <Link onClick={logout}>로그아웃</Link>
              </>
            )}
            <a href="/rewardfund" className="projAdd">
              프로젝트 만들기
            </a>
          </span>
        </div>
        <nav className="nav">
          <ul className="nav-item">
            <li>
              <a href="/">홈</a>
            </li>
            <li>
              <a href="/reward">리워드</a>
            </li>
            <li>
              <a href="/fund">투자</a>
            </li>
            {/*<li><a href="/">창업정보</a></li>*/}
            {/*<li><a href="">오픈예정</a></li>*/}
          </ul>
          <span className="searchForm">
            <form onSubmit={searchSubmit} className="searchF">
              <input
                type="text"
                id="keyword"
                className="nav-search float-r"
                placeholder="프로젝트 명/기업 명"
                maxLength="10"
                onInput={(e) => {
                  setKeyword(e.target.value);
                }}
                autoComplete="off"
              />
              <button type="submit" className="nav-search-btn">
                <SearchOutlined />
              </button>
            </form>
          </span>
        </nav>
      </div>
    </header>
  );
}

export default Header;
