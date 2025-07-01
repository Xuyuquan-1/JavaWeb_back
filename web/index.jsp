<%--
  Created by IntelliJ IDEA.
  User: 可
  Date: 2025/6/23
  Time: 02:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>JSP + React 示例</title>
    <script src="https://unpkg.com/react@18/umd/react.development.js"></script>
    <script src="https://unpkg.com/react-dom@18/umd/react-dom.development.js"></script>
    <!-- 引入 Babel 编译器 -->
    <script src="https://unpkg.com/@babel/standalone/babel.min.js"></script>
  </head>
  <body>
  <div id="root"></div>
  <script type="text/babel">
    function Profile() {
    return (
            <img
                    src="https://i.imgur.com/MK3eW3As.jpg"
                    alt="Katherine Johnson"
            />
    );
  }

  function Gallery() {
    return (
            <section>
              <h1>Amazing scientists</h1>
              <Profile />
              <Profile />
              <Profile />
            </section>
    );
  }
    // 渲染组件到 #root
  const root = ReactDOM.createRoot(document.getElementById('root'));
  root.render(<Gallery />);
  </script>
  <script>console.log('Node.js version:')</script>
  $END$
  </body>
</html>
