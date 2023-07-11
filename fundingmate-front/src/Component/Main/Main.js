import React from 'react';
import "../Content/Project.css"
import Project from "../Content/Project";

function Main() {
    return (
        <div className="container">
            <Project title="리워드 프로젝트"/>
            <Project title="창업 프로젝트"/>
            <Project title="사전공개"/>
        </div>
    );
}

export default Main;