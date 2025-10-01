# 📱 Proyecto – Aplicación de Finanzas Personales

## 👥 Integrantes del grupo
- **Gonzalo Rigoni**  
- **Romina Herrera**

---

## 📌 Descripción
Aplicación móvil desarrollada para la materia **Programación de Aplicaciones Móviles**.  
El objetivo principal es permitir a los usuarios **administrar mejor sus finanzas personales**, llevando un registro organizado de manera **simple**.

---

## 🎨 Prototipado
- 👉 [Diseño en Figma](https://www.figma.com/design/iZSdtFcI1qATdhagOOWhqA/Untitled?node-id=0-1&t=nfurr5Pq0cYAcRWb-1y)  
- 👉 [Presentacion en Canva y analisis](https://www.canva.com/design/DAGzJdiet8Y/3QgI46bYzEQsOW5H0-v_3A/edit)

---

## 💻 Repositorio
El código fuente del proyecto está disponible en GitHub:  
👉 [Repositorio en GitHub](https://github.com/Gonn01/parcial-1-am-acn4av-rigoni-herrera.git)

---

## 🚀 Funcionalidades principales

### 🔑 LoginActivity
- Permite acceder a la aplicación validando credenciales.  
- **Componentes principales**:  
  - `TextView`: título *Login*.  
  - `EditText`: usuario  
  - `EditText`: contraseña
  - `Button`: acción de login.  
- **Comportamiento**:  
  - Campos vacíos → muestra error.  
  - Credenciales inválidas → error de usuario/contraseña.  
  - Credenciales correctas → acceso al Dashboard.

---

### 🏠 DashboardActivity
- Pantalla principal tras el login.  
- Actúa como menú central con **navegación inferior**.  
- **Componentes principales**:  
  - `FrameLayout`: contenedor dinámico de fragments.  
  - `BottomNavigationView`: barra inferior de navegación.  
- **Fragments asociados**:  
  - `HomeFragment`: lista de compras.  
  - `FinancialEntitiesFragment`: lista de entidades financieras.  
- **Listas dinámicas**:  
  - `List<PurchaseHomeDto>` (compras).  
  - `List<FinancialEntityHomeDto>` (entidades).

---

## 🛠️ Tecnologías utilizadas
- **Android Studio** (IDE).  
- **Java** para la lógica de la aplicación.  
- **XML** para layouts.    
- **Figma** para prototipado UI/UX.  
- **Canva** para material visual y analisis.  
- **GitHub** para versionado y entrega.  

---

## 📈 Plan a futuro
- Carga de fotografías de facturas.  
- Inclusión de tipos de cambio.  
- Gestión de montos y cuotas.  

---

## 🙌 Agradecimientos
Proyecto realizado en el marco de la cátedra **Aplicaciones Móviles**, Escuela Da Vinci.  
Profesores: **Sergio Medina** y **Ignacio Sagnella**
