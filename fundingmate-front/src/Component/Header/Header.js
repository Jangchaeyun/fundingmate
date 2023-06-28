// components/Layout/Header/Header.tsx
function Header() {
    return (
        <header className="header">
            <div>
                <span>LOGO</span>
                <span><a href="">로그인</a></span>
                <span><a href="">프로젝트 만들기</a></span>
            </div>
            <nav>
                <ul>
                    <li><a href="">홈</a></li>
                    <li><a href="">리워드</a></li>
                    <li><a href="">창업</a></li>
                    <li><a href="">창업정보</a></li>
                    <li><a href="">오픈예정</a></li>
                </ul>
                <span>search</span>
            </nav>

            <h2>This is Header</h2>
        </header>
    )
}

export default Header