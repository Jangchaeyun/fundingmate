// components/Layout/Header/Header.tsx
import "./Header.css";
import { SearchOutlined } from "@ant-design/icons";
import { useDispatch, useSelector } from "react-redux";
import { Link, useLocation, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
function Header() {
  const token = useSelector((state) => state.Authorization);
  const userid = useSelector((state) => state.UserId);
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const { state } = useLocation();
  const [keyword, setKeyword] = useState(state ? state : "");

  const logout = () => {
    dispatch({ type: "NEWTOKEN", payload: "" });
    dispatch({ type: "USERID", payload: "" });
    // 로컬 스토리지에서 토큰 제거
    localStorage.removeItem("token");

    // isLoggedIn 상태 변경
    setIsLoggedIn(false);

    document.location.href = "/";
  };
  const searchSubmit = () => {
    if (keyword === "") {
      document.getElementById("keyword").focus();
      return;
    }
    navigate("/search", { state: keyword });
  };
  return (
    <header className="header">
      <div className="header-wrap">
        <div className="hTop">
          <span className="hlogo">
            <a href="/" className="hlogoL">
              LOGO
            </a>
          </span>
          <span className="hTopMenu">
            {(token == "" || token == undefined) && (
              <span>
                <a href="login">로그인</a>
              </span>
            )}
            <b>{userid}</b>&nbsp;&nbsp;
            {userid != "" && <Link onClick={logout}>로그아웃</Link>}
            <span>
              <a href="rewardfund">프로젝트 만들기</a>
            </span>
          </span>
        </div>
        <nav className="nav">
          <ul className="nav-item">
            <li>
              <a href="">홈</a>
            </li>
            <li>
              <a href="/reward">리워드</a>
            </li>
            <li>
              <a href="">창업</a>
            </li>
            <li>
              <a href="">창업정보</a>
            </li>
            <li>
              <a href="">오픈예정</a>
            </li>
          </ul>
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
          </form>
          <SearchOutlined className="nav-search-btn" onClick={searchSubmit} />
        </nav>
      </div>
    </header>
  );
}

export default Header;
