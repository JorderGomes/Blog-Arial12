"use client";

import Button from "@/components/Button";
import MenuContainer from "@/components/MenuContainer";

const PageContainer = ({ children, returnCallback, menuTitle }) => {
  return (
    <>
      <h1>{menuTitle}</h1>
      <MenuContainer>{children}</MenuContainer>
    </>
  );
};

export default PageContainer;
