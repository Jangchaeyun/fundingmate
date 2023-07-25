import React, {useState} from 'react';
import {SearchOutlined} from "@ant-design/icons";
import "./SearchForm.css"
import {useLocation} from "react-router-dom";
function SearchForm(props) {
    const { state } = useLocation();
    const [keyword, setKeyword] = useState(state.word ? state.word : "");
    const [reward, setReward] = useState(state.list ? state.list : [])
    return (
        <div className="searchForm">
            <div className="searchCnt">0건의 검색 결과</div>
            <div className="searchKeyword">
                <input type="text" value={keyword} onInput={(e)=>{setKeyword(e.target.value)}} className="nav-search" placeholder="프로젝트 명/기업 명" maxLength="10"/>
                <button type="submit" className="search-btn"><SearchOutlined/></button>
            </div>

            <div>
                <select name="" id="" className="searchfilter">
                    <option value="">전체보기</option>
                    <option value="">리워드</option>
                    <option value="">투자</option>
                    <option value="">오픈예정</option>
                </select>
            </div>
        </div>
    );
}

export default SearchForm;