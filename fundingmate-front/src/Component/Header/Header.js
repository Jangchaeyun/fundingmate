// components/Layout/Header/Header.tsx
import "./Header.css"
import {SearchOutlined} from "@ant-design/icons";
function Header() {
    return (
        <header className="header">
            <div className="hTop">
                <span className="hlogo">
                    <a href="" className="hlogoL">LOGO</a>
                </span>
                <span className="hTopMenu">
                    <span><a href="">로그인</a></span>
                    <span><a href="">프로젝트 만들기</a></span>
                </span>
            </div>
            <nav className="nav">
                <ul className="nav-item">
                    <li><a href="">홈</a></li>
                    <li><a href="">리워드</a></li>
                    <li><a href="">창업</a></li>
                    <li><a href="">창업정보</a></li>
                    <li><a href="">오픈예정</a></li>
                </ul>
                <input type="text" className="nav-search" placeholder="프로젝트 명/기업 명" maxLength="10"/>
                <SearchOutlined  className="nav-search-btn" style={{fontSize:"20px"}}/>
            </nav>

        </header>
    )
}

export default Header