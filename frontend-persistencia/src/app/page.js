"use client";

import Button from "@/components/Button";
import MenuContainer from "@/components/MenuContainer";
import { useRouter } from "next/navigation";

const MyApp = () => {
  const router = useRouter();
  return (
    <div>
      <h1 className="mb-5">Aplicação de gerenciamento de rede social</h1>
      <MenuContainer>
        <Button
          callback={() => router.push("/users")}
          textoDoBotao={"Menu de Usuarios"}
        ></Button>

        <Button
          callback={() => router.push("/posts")}
          textoDoBotao={"Menu de Posts"}
        ></Button>
        <Button
          callback={() => router.push("/comentarios")}
          textoDoBotao={"Menu de Comentarios"}
        ></Button>
      </MenuContainer>
    </div>
  );
};

export default MyApp;
