import React from 'react'
import "../../pages/RewardFund/RewardFundMain.css"

const RewardFundMain = () => {
  return (
    <div>
      <div className='reward_make'>
        <h1 className='makerewardfund'>프로젝트 만들기</h1>
        <div className='reward_div'>
          <div className='makereward_title'>리워드 프로젝트</div>
          <img src="/assets/imgs/reward.png" className='makereward'/>
          <button type='button' className='reward_button'>만들기</button>
        </div>
        <div className='fund_div'>
          <div className='makefund_title'>투자 프로젝트</div>
          <img src="/assets/imgs/fund.png" className='makefund'/>
          <button type='button' className='fund_button'>만들기</button>
        </div>
      </div>
    </div>
  )
}

export default RewardFundMain