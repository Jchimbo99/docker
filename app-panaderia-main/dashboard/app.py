import streamlit as st
import pandas as pd
import psycopg2
import plotly.express as px
from datetime import datetime

# CONFIGURACIÓN
st.set_page_config(
    page_title="Panadería Deluxe - Panel Administrativo",
    page_icon="🥖",
    layout="wide"
)

# CONEXIÓN BD
def conectar():
    return psycopg2.connect(
        host="postgres",
        database="panaderia",
        user="admin",
        password="12345",
        port="5432"
    )

@st.cache_data(ttl=60)
def get_data(query):
    try:
        conn = conectar()
        df = pd.read_sql(query, conn)
        conn.close()
        return df
    except Exception as e:
        st.error(e)
        return pd.DataFrame()

# HEADER
st.markdown("""
<div style='background:linear-gradient(135deg,#2C1810,#8B4513);
padding:25px;border-radius:10px;color:white;text-align:center'>
<h1>🥖 Panadería Deluxe</h1>
<p>Panel Administrativo</p>
</div>
""", unsafe_allow_html=True)

st.write("")

col1,col2,col3,col4 = st.columns(4)

# DATOS
df_productos = get_data("SELECT * FROM producto")
df_socios = get_data("SELECT * FROM suscripcion")

# METRICAS
with col1:
    st.metric("Productos", len(df_productos))

with col2:
    st.metric("Socios registrados", len(df_socios))

with col3:
    if not df_productos.empty:
        st.metric("Precio promedio", round(df_productos["precio"].mean(),2))

with col4:
    st.metric("Actualizado", datetime.now().strftime("%H:%M"))

st.divider()

# SECCIÓN SOCIOS
st.subheader("👥 Socios registrados")

if not df_socios.empty:
    st.dataframe(
        df_socios[["nombre","correo","telefono"]],
        use_container_width=True
    )
else:
    st.info("No hay socios registrados")

st.divider()

# SECCIÓN PRODUCTOS
st.subheader("📦 Productos")

if not df_productos.empty:

    st.dataframe(
        df_productos[["nombre","categoria","precio"]],
        use_container_width=True
    )

    col1,col2 = st.columns(2)

    # GRÁFICO PRECIO
    with col1:
        fig = px.bar(
            df_productos,
            x="nombre",
            y="precio",
            color="categoria",
            title="Precio por producto"
        )
        st.plotly_chart(fig,use_container_width=True)

    # GRÁFICO CATEGORIAS
    with col2:
        fig2 = px.pie(
            df_productos,
            names="categoria",
            title="Distribución por categoría"
        )
        st.plotly_chart(fig2,use_container_width=True)

else:
    st.warning("No hay productos registrados")

st.divider()

st.caption("Panadería Deluxe · Dashboard Administrativo")