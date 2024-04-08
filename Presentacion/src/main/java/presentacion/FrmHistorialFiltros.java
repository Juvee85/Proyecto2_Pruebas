/*
 * FrmHistorialFiltros.java
 */
package presentacion;

import dtos.PersonaDTO;
import dtos.TramiteDTO;
import excepciones.NegocioException;
import excepciones.PresentacionException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import negocio.ConsultaHistorialBO;
import negocio.IConsultaHistorialBO;
import utilidades.JButtonCellEditor;
import utilidades.JButtonRenderer;
import utilidades.Paleta;
import utilidades.TipoConsulta;
import static utilidades.TipoConsulta.ANIO_NACIMIENTO;
import static utilidades.TipoConsulta.CURP;
import static utilidades.TipoConsulta.INICIO;
import static utilidades.TipoConsulta.NOMBRE;
import utilidades.Validadores;

/**
 * Ventana donde se filtran a las personas de las cuales se quiere conocer el
 * historial de trámites.
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public class FrmHistorialFiltros extends javax.swing.JFrame {

    private int mouseX, mouseY;
    private TipoConsulta tipo = TipoConsulta.INICIO;
    private IConsultaHistorialBO consultas;
    private int pagina = 1;
    private int numeroFilas = 4;

    /**
     * Constructor del frame que inicializa los atributos.
     */
    public FrmHistorialFiltros() {
        initComponents();

        this.consultas = new ConsultaHistorialBO(numeroFilas);

        // Cargamos los datos de las personas que haya registradas.
        cargarDatos();
        // Le damos formato a la tabla.
        formatearTabla();
    }

    /**
     * Método para darle formato a la tabla.
     */
    private void formatearTabla() {
        // Cambiamos el color del fondo.
        tblPersonas.getTableHeader().setBackground(new Color(106, 27, 49));
        // Cambiamos la fuente y el tamaño.
        tblPersonas.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
        // Cambiamos el color de la letra.
        tblPersonas.getTableHeader().setForeground(new Color(188, 149, 92));

        // Creamos el evento de cuando le pican al botón para ver el historial
        // de una persona.
        ActionListener onEditarClickListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Metodo para seleccionar una persona.
                seleccionar();
            }
        };
        int indiceColumnaSeleccionar = 3;
        TableColumnModel modeloColumnas = this.tblPersonas.getColumnModel();
        modeloColumnas.getColumn(indiceColumnaSeleccionar).setCellRenderer(new JButtonRenderer("Seleccionar"));
        modeloColumnas.getColumn(indiceColumnaSeleccionar).setCellEditor(new JButtonCellEditor("Seleccionar", onEditarClickListener));
    }

    /**
     * Método para seleccionar a una persona de la tabla.
     */
    private void seleccionar() {
        // Obtenemos la CURP de la persona seleccionada.
        String curp = this.getCurpSeleccionada();
        PersonaDTO persona = null;
        try {
            // Obtenemos a la persona.
            persona = consultas.obtenerPersonaPorCURP(curp);
            // Obtenemos los trámites que haya realizado la persona.
            List<TramiteDTO> lista = consultas.obtenerTramitesPorPersona(curp, pagina);
            if (lista.isEmpty()) {
                // Si no ha realizado ninguno, mandamos un mensaje indicando esto.
                JOptionPane.showMessageDialog(this, "Esta persona no ha realizado trámites.", "¡Oops!", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NegocioException ne) {
            // Mandamos un mensaje de que sucedió un error inesperado.
            JOptionPane.showMessageDialog(this, ne.getMessage(), "¡Error!", JOptionPane.ERROR_MESSAGE);
        }
        // Redireccionamos a la ventana de los resultados.
        FrmHistorialResultados frmHistorialResultados = new FrmHistorialResultados(persona);
        frmHistorialResultados.setVisible(true);
        this.dispose();
    }

    /**
     * Método para obtener la CURP de la persona seleccionada.
     *
     * @return La CURP de la persona seleccionada, null si el indice de fila es
     * negativo.
     */
    private String getCurpSeleccionada() {
        // Obtenemos el índice de la fila seleccionada.
        int indiceFilaSeleccionada = this.tblPersonas.getSelectedRow();
        if (indiceFilaSeleccionada != -1) { // Si es positivo.
            DefaultTableModel modelo = (DefaultTableModel) this.tblPersonas.getModel();
            int indiceColumnaCurp = 0;
            // Obtenemos la CURP.
            String curpPersonaSeleccionado = (String) modelo.getValueAt(indiceFilaSeleccionada, indiceColumnaCurp);
            return curpPersonaSeleccionado;
        } else {
            return null;
        }
    }

    /**
     * Método para cargar los datos de los trámites.
     */
    private void cargarDatos() {
        List<PersonaDTO> personas = new ArrayList<>();
        Validadores v = new Validadores(); // Instancia para validar la CURP.
        try {
            switch (tipo) { // Evalúamos la posibilidad de que se filtre por cada tipo.
                case CURP -> {
                    // Validamos la CURP.
                    v.validarCurp(txtCurp.getText());
                    // Buscamos una persona y la añadimos la lista.
                    personas.add(consultas.obtenerPersonaPorCURP(txtCurp.getText()));
                }
                case ANIO_NACIMIENTO -> {
                    // Buscamos personas que coincidan y las añadimos a la lista.
                    personas = consultas.obtenerPersonasPorAnioNacimiento(Integer.parseInt(txtAnioNacimiento.getText()), pagina);
                }
                case NOMBRE -> {
                    // Buscamos personas que coincidan y las añadimos a la lista.
                    personas = consultas.obtenerPersonasPorNombre(txtNombre.getText(), pagina);
                }
                default ->
                    // Buscamos a todas las personas y las añadimos a la lista.
                    personas = consultas.obtenerPersonas(pagina);
            }
        } catch (NegocioException | PresentacionException ex) {
            // Mandamos un error de validación de CURP o de un error inesperado.
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            // Mandamos un mensaje si se ingresan letras en el año.
            JOptionPane.showMessageDialog(this, "El año ingresado debe ser un número.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        // Si la lista de personas es null o está vacía.
        if (personas == null || personas.isEmpty()) {
            if (pagina > 1) { // Si estamos en una página mayor a la primera.
                pagina--; // Decrementamos el número de página.
            }
            // Cambiamos el texto de la página.
            lblPagina.setText("Página " + pagina);
            // Mandamos un mensaje de que no se encontraron personas.
            JOptionPane.showMessageDialog(this, "No se encontraron personas.", "Error", JOptionPane.WARNING_MESSAGE);
        } else {
            // Si sí se encontraron personas, mandamos a llenar la tabla.
            llenarTablaPersonas(personas);
        }
    }

    /**
     * Método para llenar la tabla de personas.
     *
     * @param listaPersonas Lista con las personas que se encontraron.
     */
    private void llenarTablaPersonas(List<PersonaDTO> listaPersonas) {
        DefaultTableModel modeloTabla = (DefaultTableModel) tblPersonas.getModel();

        if (modeloTabla.getRowCount() > 0) {
            for (int i = modeloTabla.getRowCount() - 1; i > -1; i--) {
                modeloTabla.removeRow(i);
            }
        }

        if (listaPersonas != null) {
            listaPersonas.forEach(row -> {
                Object[] fila = new Object[3];
                fila[0] = row.getCurp();
                fila[1] = row.getNombre();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String fecha = formatter.format(row.getFechaNacimiento().getTime());
                fila[2] = fecha;

                modeloTabla.addRow(fila);
            });
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlBarra = new javax.swing.JPanel();
        pnlHeader = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnMinimizar = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        pnlTitulo = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        pnlContenido = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        pnlCampos = new javax.swing.JPanel();
        txtNombre = new javax.swing.JTextField();
        txtCurp = new javax.swing.JTextField();
        txtAnioNacimiento = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        nombreRadioBtn = new javax.swing.JRadioButton();
        anioNacimientoRadioBtn = new javax.swing.JRadioButton();
        curpRadioBtn = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPersonas = new javax.swing.JTable();
        btnVolver = new javax.swing.JButton();
        btnSiguiente = new javax.swing.JButton();
        btnAnterior = new javax.swing.JButton();
        lblPagina = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlHeader.setBackground(new java.awt.Color(88, 88, 88));
        pnlHeader.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                pnlHeaderMouseDragged(evt);
            }
        });
        pnlHeader.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlHeaderMousePressed(evt);
            }
        });
        pnlHeader.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(242, 242, 242));
        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(242, 242, 242));
        jLabel1.setText("Sistema de tránsito");
        pnlHeader.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

        btnMinimizar.setBackground(new java.awt.Color(88, 88, 88));
        btnMinimizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMinimizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMinimizarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMinimizarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMinimizarMouseExited(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("-");

        javax.swing.GroupLayout btnMinimizarLayout = new javax.swing.GroupLayout(btnMinimizar);
        btnMinimizar.setLayout(btnMinimizarLayout);
        btnMinimizarLayout.setHorizontalGroup(
            btnMinimizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnMinimizarLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        btnMinimizarLayout.setVerticalGroup(
            btnMinimizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnMinimizarLayout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlHeader.add(btnMinimizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 0, 30, 30));

        btnCerrar.setBackground(new java.awt.Color(88, 88, 88));
        btnCerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCerrarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCerrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCerrarMouseExited(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("X");

        javax.swing.GroupLayout btnCerrarLayout = new javax.swing.GroupLayout(btnCerrar);
        btnCerrar.setLayout(btnCerrarLayout);
        btnCerrarLayout.setHorizontalGroup(
            btnCerrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );
        btnCerrarLayout.setVerticalGroup(
            btnCerrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        pnlHeader.add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 0, -1, 30));

        pnlTitulo.setBackground(new java.awt.Color(106, 27, 49));
        pnlTitulo.setPreferredSize(new java.awt.Dimension(700, 125));
        pnlTitulo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo.png"))); // NOI18N
        pnlTitulo.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 25, -1, -1));

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 48)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(188, 149, 92));
        jLabel5.setText("Sistema de tránsito");
        pnlTitulo.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(262, 25, -1, 100));

        javax.swing.GroupLayout pnlBarraLayout = new javax.swing.GroupLayout(pnlBarra);
        pnlBarra.setLayout(pnlBarraLayout);
        pnlBarraLayout.setHorizontalGroup(
            pnlBarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBarraLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pnlBarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        pnlBarraLayout.setVerticalGroup(
            pnlBarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBarraLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pnlHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(pnlBarra, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 180));

        pnlContenido.setBackground(new java.awt.Color(242, 242, 242));

        jLabel7.setFont(new java.awt.Font("SansSerif", 0, 40)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Historial de trámites");

        pnlCampos.setBackground(new java.awt.Color(242, 242, 242));

        txtNombre.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        txtNombre.setEnabled(false);
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        txtCurp.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        txtCurp.setForeground(new java.awt.Color(37, 37, 37));
        txtCurp.setEnabled(false);

        txtAnioNacimiento.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        txtAnioNacimiento.setEnabled(false);
        txtAnioNacimiento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAnioNacimientoKeyTyped(evt);
            }
        });

        btnBuscar.setBackground(new java.awt.Color(106, 27, 49));
        btnBuscar.setFont(new java.awt.Font("SansSerif", 1, 17)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(242, 242, 242));
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/buscar.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscar.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnBuscar.setPreferredSize(new java.awt.Dimension(120, 40));
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        nombreRadioBtn.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        nombreRadioBtn.setText("Nombre");
        nombreRadioBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreRadioBtnActionPerformed(evt);
            }
        });

        anioNacimientoRadioBtn.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        anioNacimientoRadioBtn.setText("Año de nacimiento");
        anioNacimientoRadioBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anioNacimientoRadioBtnActionPerformed(evt);
            }
        });

        curpRadioBtn.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        curpRadioBtn.setText("CURP");
        curpRadioBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                curpRadioBtnActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("Coincidencias");

        javax.swing.GroupLayout pnlCamposLayout = new javax.swing.GroupLayout(pnlCampos);
        pnlCampos.setLayout(pnlCamposLayout);
        pnlCamposLayout.setHorizontalGroup(
            pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCamposLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCamposLayout.createSequentialGroup()
                        .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtAnioNacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                .addComponent(txtCurp))
                            .addComponent(curpRadioBtn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCamposLayout.createSequentialGroup()
                                .addComponent(nombreRadioBtn)
                                .addGap(180, 180, 180))
                            .addGroup(pnlCamposLayout.createSequentialGroup()
                                .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())))
                    .addGroup(pnlCamposLayout.createSequentialGroup()
                        .addComponent(anioNacimientoRadioBtn)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        pnlCamposLayout.setVerticalGroup(
            pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCamposLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombreRadioBtn)
                    .addComponent(curpRadioBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCurp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addComponent(anioNacimientoRadioBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAnioNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblPersonas.setBackground(new java.awt.Color(11, 35, 30));
        tblPersonas.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        tblPersonas.setForeground(new java.awt.Color(242, 242, 242));
        tblPersonas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "CURP", "Nombre", "Fecha de nacimiento", "Acción"
            }
        ));
        tblPersonas.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblPersonas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblPersonas.setName(""); // NOI18N
        tblPersonas.setSelectionBackground(new java.awt.Color(106, 27, 49));
        tblPersonas.setSelectionForeground(new java.awt.Color(242, 242, 242));
        tblPersonas.setShowGrid(true);
        tblPersonas.setSurrendersFocusOnKeystroke(true);
        jScrollPane1.setViewportView(tblPersonas);

        btnVolver.setBackground(new java.awt.Color(11, 35, 30));
        btnVolver.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnVolver.setForeground(new java.awt.Color(242, 242, 242));
        btnVolver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/atras.png"))); // NOI18N
        btnVolver.setText("Volver");
        btnVolver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVolver.setPreferredSize(new java.awt.Dimension(120, 40));
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        btnSiguiente.setBackground(new java.awt.Color(188, 149, 92));
        btnSiguiente.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnSiguiente.setForeground(new java.awt.Color(242, 242, 242));
        btnSiguiente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/siguiente.png"))); // NOI18N
        btnSiguiente.setText("Siguiente");
        btnSiguiente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSiguiente.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnSiguiente.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        btnSiguiente.setPreferredSize(new java.awt.Dimension(120, 40));
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });

        btnAnterior.setBackground(new java.awt.Color(188, 149, 92));
        btnAnterior.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnAnterior.setForeground(new java.awt.Color(242, 242, 242));
        btnAnterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/anterior.png"))); // NOI18N
        btnAnterior.setText("Anterior");
        btnAnterior.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAnterior.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAnterior.setPreferredSize(new java.awt.Dimension(120, 40));
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });

        lblPagina.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblPagina.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPagina.setText("Página 1");
        lblPagina.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout pnlContenidoLayout = new javax.swing.GroupLayout(pnlContenido);
        pnlContenido.setLayout(pnlContenidoLayout);
        pnlContenidoLayout.setHorizontalGroup(
            pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenidoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(pnlContenidoLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlContenidoLayout.createSequentialGroup()
                        .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(btnAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPagina, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlCampos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        pnlContenidoLayout.setVerticalGroup(
            pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenidoLayout.createSequentialGroup()
                .addComponent(jLabel7)
                .addGap(12, 12, 12)
                .addComponent(pnlCampos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblPagina)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(pnlContenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 700, 450));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método que reacciona al evento de dar clic en el botón para minimizar la
     * ventana.
     *
     * @param evt Evento del mouse al que se escucha.
     */
    private void btnMinimizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizarMouseClicked
        this.setState(Frame.ICONIFIED);
    }//GEN-LAST:event_btnMinimizarMouseClicked

    /**
     * Método que cambia el color del botón para minimizar la ventana cuando se
     * pasa el mouse por encima.
     *
     * @param evt Evento del mouse al que se escucha.
     */
    private void btnMinimizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizarMouseEntered
        btnMinimizar.setBackground(Paleta.VERDE);
    }//GEN-LAST:event_btnMinimizarMouseEntered

    /**
     * Método que cambia el color del botón para minimizar la ventana a su color
     * original.
     *
     * @param evt Evento del mouse al que se escucha.
     */
    private void btnMinimizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizarMouseExited
        btnMinimizar.setBackground(Paleta.GRIS);
    }//GEN-LAST:event_btnMinimizarMouseExited

    /**
     * Método que reacciona al evento de dar clic en el botón para cerrar la
     * ventana.
     *
     * @param evt Evento del mouse al que se escucha.
     */
    private void btnCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseClicked
        System.exit(0);
    }//GEN-LAST:event_btnCerrarMouseClicked

    /**
     * Método que cambia el color del botón para cerrar la ventana cuando se
     * pasa el mouse por encima.
     *
     * @param evt Evento del mouse al que se escucha.
     */
    private void btnCerrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseEntered
        btnCerrar.setBackground(Paleta.ROJO);
    }//GEN-LAST:event_btnCerrarMouseEntered

    /**
     * Método que cambia el color del botón para cerrar la ventana a su color
     * original.
     *
     * @param evt Evento del mouse al que se escucha.
     */
    private void btnCerrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseExited
        btnCerrar.setBackground(Paleta.GRIS);
    }//GEN-LAST:event_btnCerrarMouseExited

    /**
     * Método que mueve la ventana cuando se arrastra el mouse por el header.
     *
     * @param evt Evento del mouse al que se escucha.
     */
    private void pnlHeaderMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlHeaderMouseDragged
        // Obtenemos las coordenadas del mouse en la pantalla.
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();

        // Se calcula la distancia del recorrido del mouse y eso es lo que se
        // mueve la ventana.
        this.setLocation(x - mouseX, y - mouseY);
    }//GEN-LAST:event_pnlHeaderMouseDragged

    /**
     * Método que registra las coordenadas del mouse cuando presiona el header.
     *
     * @param evt Evento del mouse al que se escucha.
     */
    private void pnlHeaderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlHeaderMousePressed
        // Se actualizan las coordenadas.
        mouseX = evt.getX();
        mouseY = evt.getY();
    }//GEN-LAST:event_pnlHeaderMousePressed

    /**
     * Método que reacciona al evento de dar clic en el botón para regresar a la
     * pantalla anterior.
     *
     * @param evt Evento del mouse al que se escucha.
     */
    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        FrmHome frmHome = new FrmHome();
        frmHome.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnVolverActionPerformed

    /**
     * Método que reacciona al evento de dar clic en el botón para buscar una
     * persona según el filtro seleccionado.
     *
     * @param evt Evento del mouse al que se escucha.
     */
    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // Nos vamos a la página 1 y cargamos los datos.
        pagina = 1;
        cargarDatos();
    }//GEN-LAST:event_btnBuscarActionPerformed

    /**
     * Método que reacciona al evento de dar clic en el botón para ir a la
     * página siguiente de la tabla.
     *
     * @param evt Evento del mouse al que se escucha.
     */
    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        pagina++; // Aumentamos la página.
        cargarDatos(); // Cargamos los datos.
        lblPagina.setText("Página " + pagina); // Mostramos el nuevo número de página.
    }//GEN-LAST:event_btnSiguienteActionPerformed

    /**
     * Método que reacciona al evento de dar clic en el botón para ir a la
     * página anterior de la tabla.
     *
     * @param evt Evento del mouse al que se escucha.
     */
    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        if (pagina > 1) { // Si estamos en otra página que no sea la 1.
            pagina--; // Disminuímos la página.
            cargarDatos(); // Cargamos los datos.
            lblPagina.setText("Página " + pagina); // Mostramos el nuevo número de página.
        }
    }//GEN-LAST:event_btnAnteriorActionPerformed

    /**
     * Método que reacciona al evento de dar clic en el radio button para
     * habilitar o deshabilitar el filtro de nombre.
     *
     * @param evt Evento del mouse al que se escucha.
     */
    private void nombreRadioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreRadioBtnActionPerformed
        if (nombreRadioBtn.isSelected()) {
            // Se deshabilitan los otros filtros.
            anioNacimientoRadioBtn.setSelected(false);
            txtAnioNacimiento.setEnabled(false);
            curpRadioBtn.setSelected(false);
            txtCurp.setEnabled(false);
            txtNombre.setEnabled(true);
            txtNombre.requestFocus();
            tipo = NOMBRE; // Indicamos que el filtro es por nombre.
        } else {
            // Deshabilitamos el radio button e indicamos que el filtro es el inicial.
            txtNombre.setEnabled(false);
            tipo = INICIO;
        }
    }//GEN-LAST:event_nombreRadioBtnActionPerformed

    /**
     * Método que reacciona al evento de dar clic en el radio button para
     * habilitar o deshabilitar el filtro de año de nacimiento.
     *
     * @param evt Evento del mouse al que se escucha.
     */
    private void anioNacimientoRadioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anioNacimientoRadioBtnActionPerformed
        if (anioNacimientoRadioBtn.isSelected()) {
            // Se deshabilitan los otros filtros.
            nombreRadioBtn.setSelected(false);
            txtNombre.setEnabled(false);
            curpRadioBtn.setSelected(false);
            txtCurp.setEnabled(false);
            txtAnioNacimiento.setEnabled(true);
            txtAnioNacimiento.requestFocus();
            tipo = ANIO_NACIMIENTO; // Indicamos que el filtro es por año de nacimiento.
        } else {
            // Deshabilitamos el radio button e indicamos que el filtro es el inicial.
            txtAnioNacimiento.setEnabled(true);
            tipo = INICIO;
        }
    }//GEN-LAST:event_anioNacimientoRadioBtnActionPerformed

    /**
     * Método que reacciona al evento de dar clic en el radio button para
     * habilitar o deshabilitar el filtro de CURP.
     *
     * @param evt Evento del mouse al que se escucha.
     */
    private void curpRadioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_curpRadioBtnActionPerformed
        if (curpRadioBtn.isSelected()) {
            // Se deshabilitan los otros filtros.
            anioNacimientoRadioBtn.setSelected(false);
            txtAnioNacimiento.setEnabled(false);
            nombreRadioBtn.setSelected(false);
            txtNombre.setEnabled(false);
            txtCurp.setEnabled(true);
            txtCurp.requestFocus();
            tipo = CURP; // Indicamos que el filtro es por CURP.
        } else {
            // Deshabilitamos el radio button e indicamos que el filtro es el inicial.
            txtCurp.setEnabled(false);
            tipo = INICIO;
        }
    }//GEN-LAST:event_curpRadioBtnActionPerformed

    /**
     * Método para evitar que se ingresen letras en el campo de texto año.
     *
     * @param evt
     */
    private void txtAnioNacimientoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAnioNacimientoKeyTyped
        // Se obtiene el caracter ingresado.
        char c = evt.getKeyChar();
        // Si es diferente a un dígito y al retroceso.
        if ((c < '0' || c > '9') && c != KeyEvent.VK_BACK_SPACE) {
            // Se destruye el evento (no se pone el caracter).
            evt.consume();
        }
    }//GEN-LAST:event_txtAnioNacimientoKeyTyped

    /**
     * Método para evitar que se ingresen números en el campo de texto del
     * nombre.
     *
     * @param evt
     */
    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        // Se obtiene el caracter ingresado.
        char c = evt.getKeyChar();
        // Si no es una letra o el retroceso.
        if (!Character.isLetter(c) && c != KeyEvent.VK_BACK_SPACE) {
            // Se destruye el evento (no se pone el caracter).
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton anioNacimientoRadioBtn;
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JPanel btnCerrar;
    private javax.swing.JPanel btnMinimizar;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JButton btnVolver;
    private javax.swing.JRadioButton curpRadioBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPagina;
    private javax.swing.JRadioButton nombreRadioBtn;
    private javax.swing.JPanel pnlBarra;
    private javax.swing.JPanel pnlCampos;
    private javax.swing.JPanel pnlContenido;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JTable tblPersonas;
    private javax.swing.JTextField txtAnioNacimiento;
    private javax.swing.JTextField txtCurp;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables

}
