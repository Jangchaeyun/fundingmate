// components/Layout/Header/Header.tsx
import "./Footer.css";
function Footer() {
  return (
    <footer className="footer">
      <div className="f-logo logo">FundingMate</div>
      <div className="f-title">투자위험고지</div>
      <div className="f-content">
        <div>
          창업 투자는 원금 손실과 유동성 및 현금성에 대한 투자위험을 가지고
          있습니다.
        </div>
        <div>투자 전에 투자위험고지를 꼭 확인해주세요.</div>
        <div>
          펀딩메이트는 중개업(온라인소액투자중개 및 통신판매중개)을 영위하는
          플랫폼 제공자로
          <br />
          자금을 모집하는당사자가 아닙니다.
        </div>
        <div>
          따라서 투자손실의 위험을 보전하거나 리워드 제공을 보장해 드리지 않으며
          <br />
          이에 대한 법적인책임을 지지 않습니다.
        </div>
      </div>
    </footer>
  );
}

export default Footer;
