"use client";

import Button from "@/components/Button";
import MenuContainer from "@/components/MenuContainer";

const SubPageContainer = ({ children, returnCallback, menuTitle }) => {
  return (
    <>
      <div style={{ display: "flex", alignContent: "start", width: "100%" }}>
        <Button callback={returnCallback} textoDoBotao={"Voltar"}></Button>
      </div>
      <h2 className="mb-5">{menuTitle}</h2>
      <MenuContainer>{children}</MenuContainer>
    </>
  );
};

export default SubPageContainer;
