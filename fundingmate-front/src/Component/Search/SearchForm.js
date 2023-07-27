import React, {useState} from 'react';
import {SearchOutlined} from "@ant-design/icons";
import "./SearchForm.css"
import {useLocation, useNavigate, useParams} from "react-router-dom";
function SearchForm(props) {
    // const {keyword} = useParams();
    const { state } = useLocation();
    // const { count } = useParams();
    const [word, setWord] = useState(props.word ? props.word : "");
    const [count, setCount] = useState(props.count ? props.count : "");
    // const [reward, setReward] = useState(state.list ? state.list : [])
    const navigate = useNavigate();
    const [selectedOption, setSelectedOption] = useState('');
    const allItems = [
        { id: 1, name: '리워드 아이템 1' },
        { id: 2, name: '리워드 아이템 2' },
        { id: 3, name: '투자 아이템 1' },
        { id: 4, name: '투자 아이템 2' },
        { id: 5, name: '오픈예정 아이템 1' },
        { id: 6, name: '오픈예정 아이템 2' },
    ];
    const searchSubmit = (e) => {
        if (word.trim() === '') {
            e.preventDefault();
            document.getElementById("word").value = '';
            document.getElementById("word").focus();
            return;
        }else {
            navigate(`/search/${word}`);
        }
    }

    const changeSelect = (event) => {
        setSelectedOption(event.target.value);
        navigate(`/search/${word}/${selectedOption}`);
    };
    return (
        <div className="searchForm">
            {/*<div className="searchCnt">{count}건의 검색 결과</div>*/}
            <div className="searchKeyword">
                <form onSubmit={searchSubmit} className="searchF">
                    <input type="text" id="word" value={word} onInput={(e)=>{setWord(e.target.value)}} className="searchForm-nav-search" placeholder="프로젝트 명/기업 명" maxLength="10"/>
                    <button type="submit" className="search-btn"><SearchOutlined/></button>
                </form>
            </div>

            <div>
                {/*<select name="" id="" className="searchfilter" onChange={changeSelect}>*/}
                {/*    <option value="">전체보기</option>*/}
                {/*    <option value="리워드">리워드</option>*/}
                {/*    <option value="투자">투자</option>*/}
                {/*    /!*<option value="오픈예정">오픈예정</option>*!/*/}
                {/*</select>*/}
            </div>
        </div>
    );
}

export default SearchForm;