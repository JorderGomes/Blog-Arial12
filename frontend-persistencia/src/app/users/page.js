"use client";

import Button from "@/components/Button";
import MenuContainer from "@/components/MenuContainer";
import SubPageContainer from "@/components/SubPageContainer";
import { useRouter } from "next/navigation";

const UsersPage = () => {
  const router = useRouter();
  return (
    <SubPageContainer
      returnCallback={() => router.push("/")}
      menuTitle={"Menu de funcionalidades de usuário"}
    >
      <Button
        callback={() => router.push("/users/create")}
        textoDoBotao={"Criar usuário"}
      ></Button>
      <Button
        callback={() => router.push("/users/list")}
        textoDoBotao={"Listar usuários"}
      ></Button>
      <Button
        callback={() => router.push("/users/find")}
        textoDoBotao={"Buscar usuário"}
      ></Button>
    </SubPageContainer>
  );
};

export default UsersPage;
