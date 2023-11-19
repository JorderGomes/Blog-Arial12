"use client";

import Button from "@/components/Button";
import SubPageContainer from "@/components/SubPageContainer";
import { useRouter } from "next/navigation";

const UsersPage = () => {
  const router = useRouter();
  return (
    <SubPageContainer
      returnCallback={() => router.push("/")}
      menuTitle={"Menu de funcionalidades de posts"}
    >
      <Button
        callback={() => router.push("/posts/list")}
        textoDoBotao={"Listar posts"}
      ></Button>

      <Button
        callback={() => router.push("/posts/findById")}
        textoDoBotao={"Buscar post por id"}
      ></Button>

      <Button
        callback={() => router.push("/posts/create")}
        textoDoBotao={"Criar post"}
      ></Button>

      <Button
        callback={() => router.push("/posts/count")}
        textoDoBotao={"Contar posts"}
      ></Button>

      <Button
        callback={() => router.push("/posts/countByCategory")}
        textoDoBotao={"Contar posts por categoria"}
      ></Button>

      <Button
        callback={() => router.push("/posts/findByTitle")}
        textoDoBotao={"Buscar posts por título"}
      ></Button>

      <Button
        callback={() => router.push("/posts/findByContent")}
        textoDoBotao={"Buscar posts por conteúdo"}
      ></Button>

      <Button
        callback={() => router.push("/posts/findByRate")}
        textoDoBotao={"Buscar posts por nota"}
      ></Button>
    </SubPageContainer>
  );
};

export default UsersPage;
