"use client";

import Button from "@/components/Button";
import SubPageContainer from "@/components/SubPageContainer";
import { useRouter } from "next/navigation";

const ComentariosPage = () => {
  const router = useRouter();
  return (
    <SubPageContainer
      returnCallback={() => router.push("/")}
      menuTitle={"Menu de funcionalidades de comentário"}
    >
      <Button
        callback={() => router.push("/comentarios/findByPostId")}
        textoDoBotao={"Procurar comentários por id de post"}
      ></Button>
      <Button
        callback={() => router.push("/comentarios/find")}
        textoDoBotao={"Procurar comentário por id"}
      ></Button>
    </SubPageContainer>
  );
};

export default ComentariosPage;
