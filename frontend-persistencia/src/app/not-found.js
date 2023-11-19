"use client";

import PageContainer from "@/components/PageContainer";
import Button from "@/components/Button";
import { useRouter } from "next/navigation";

export default function NotFound({ error, reset }) {
  const router = useRouter();
  return (
    <PageContainer menuTitle={"Página não encontrada!"}>
      <Button
        textoDoBotao={"Volte para o menu inicial"}
        callback={() => router.push("/")}
      ></Button>
    </PageContainer>
  );
}
