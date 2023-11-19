export const metadata = {
  title: "Site de teste",
  description: "esse é um site de teste",
  visualViewport: {
    width: "device-width",
    height: "device-height",
    initialScale: 1,
  },
  charset: "utf-8",
};
import "@/styles/globals.css";
import 'bootstrap/dist/css/bootstrap.min.css';

const RootLayout = ({ children }) => (
  <html lang="pt-BR">
    <body>
      <div
        style={{
          display: "flex",
          flexDirection: "column",
          width: "100vw",
          height: "100vh",
          padding: "1rem",
          alignItems: "center",
        }}
      >
        {children}
      </div>
    </body>
  </html>
);
export default RootLayout;
