/*
 * FrmLicenciaDatos.java
 */
package presentacion;

import dtos.LicenciaDTO;
import dtos.TarifaLicenciaDTO;
import dtos.PersonaDTO;
import excepciones.NegocioException;
import excepciones.PresentacionException;
import java.awt.Frame;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import negocio.IRegistroLicenciaBO;
import negocio.RegistroLicenciaBO;
import utilidades.Paleta;
import utilidades.FormatoDinero;
import utilidades.Validadores;

/**
 * Ventana donde se plasman los datos del solicitante y de la licencia.
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public class FrmLicenciaDatos extends javax.swing.JFrame {

    private IRegistroLicenciaBO registroLicencia;
    private PersonaDTO persona;
    private int mouseX, mouseY;

    /**
     * Constructor del frame que inicializa los atributos.
     *
     * @param persona Persona solicitante que quiere tramitar una licencia.
     */
    public FrmLicenciaDatos(PersonaDTO persona) {
        initComponents();

        this.registroLicencia = new RegistroLicenciaBO();
        this.persona = persona;

        // Si la persona es diferente a null, significa que quiso tramitar placas
        // pero no contaba con una licencia.
        if (persona != null) {
            // Se habilitan el botón de continuar y el combobox de tarifas.
            btnConfirmar.setEnabled(true);
            comboTarifa.setEnabled(true);
            // Se deshabilita el botón de buscar.
            btnBuscar.setEnabled(false);
            // Mostramos los datos de la persona.
            mostrarDatosPersona();
        }
    }

    /**
     * Método para mostrar los datos de la persona encontrada.
     *
     * @param persona Persona encontrada.
     */
    private void mostrarDatosPersona() {
        txtCurp.setText(persona.getCurp()); // Mostramos la CURP.

        lblNombre.setText(persona.getNombre() + " " // Mostramos el nombre completo.
                + persona.getApellidoPaterno() + " "
                + persona.getApellidoMaterno());

        // Creamos un objeto para darle formato a la fecha de nacimiento.
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        // Mostramos la fehca de nacimiento.
        lblFechaNacimiento.setText(sdf.format(persona.getFechaNacimiento().getTime()));

        if (persona.getTelefono() != null) {
            lblTelefono.setText(persona.getTelefono()); // Mostramos el teléfono.
        } else {
            lblTelefono.setText("No tiene"); // Por si la persona no tiene teléfono.
        }

        if (persona.getRfc() != null) {
            lblRfc.setText(persona.getRfc()); // Mostramos el RFC.
        } else {
            lblRfc.setText("No tiene"); // Por si la persona no tiene teléfono.
        }

        // Se indica si la persona está discapacitada o no.
        if (persona.isDiscapacitado()) {
            lblDiscapacitado.setText("Sí");
        } else {
            lblDiscapacitado.setText("No");
        }

        // Cargamos las tarifas de licencia de acuerdo a si la persona está
        // o no discapacitada.
        cargarTarifasLicencia();
    }

    /**
     * Método para obtener las tarifas disponibles.
     */
    private void cargarTarifasLicencia() {
        // Creamos un modelo para combo box.
        DefaultComboBoxModel<TarifaLicenciaDTO> modelo = new DefaultComboBoxModel<>();

        // Obtenemos una lista con las distintas tarifas que hay disponibles .
        List<TarifaLicenciaDTO> tarifasLicencia = registroLicencia.buscarTarifasLicencia();

        // Iteramos sobre la lista de tarifas y vamos agregando cada una al modelo.
        for (TarifaLicenciaDTO tarifa : tarifasLicencia) {
            modelo.addElement(tarifa);
        }
        // Asignamos el modelo al combo box de tarifas.
        comboTarifa.setModel(modelo);
        // Calculamos el costo de la licencia.
        calcularCosto();
    }

    /**
     * Método para calcular el costo de la licencia de acuerdo a la vigencia y
     * si el solicitante está discapacitado o no.
     */
    public void calcularCosto() {
        // Creamos una instancia del formateador de dinero.
        FormatoDinero fd = new FormatoDinero();

        // Obtenemos la tarifa/vigencia seleccionada.
        TarifaLicenciaDTO tarifaSeleccionada = (TarifaLicenciaDTO) comboTarifa.getSelectedItem();

        String costo;
        if (persona.isDiscapacitado()) { // Si la persona está discapacitada.
            // Se obtiene el costo normal de la licencia y se le da formato.
            costo = fd.formatear(tarifaSeleccionada.getCostoDiscapacitado());
        } else { // Si la persona no está discapacitada.
            // Se obtiene el costo discapacitado de la licencia y se le da formato.
            costo = fd.formatear(tarifaSeleccionada.getCostoNormal());
        }

        // Se muestra el costo.
        lblCosto.setText(costo);
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
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtCurp = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblFechaNacimiento = new javax.swing.JLabel();
        lblTelefono = new javax.swing.JLabel();
        lblRfc = new javax.swing.JLabel();
        lblDiscapacitado = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        comboTarifa = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        lblCosto = new javax.swing.JLabel();
        btnConfirmar = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();

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

        jLabel5.setText("Sistema de tránsito");
        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 48)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(188, 149, 92));
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
        jLabel7.setText("Trámite de licencias");

        pnlCampos.setBackground(new java.awt.Color(242, 242, 242));

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(37, 37, 37));
        jLabel8.setText("Nombre:");

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(37, 37, 37));
        jLabel9.setText("Teléfono:");

        jLabel11.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(37, 37, 37));
        jLabel11.setText("CURP:");

        txtCurp.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        txtCurp.setForeground(new java.awt.Color(37, 37, 37));

        jLabel14.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(37, 37, 37));
        jLabel14.setText("Fecha de nacimiento:");

        jLabel15.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(37, 37, 37));
        jLabel15.setText("Discapacitado/a:");

        jSeparator1.setForeground(new java.awt.Color(106, 27, 49));

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(37, 37, 37));
        jLabel6.setText("RFC:");

        lblNombre.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(37, 37, 37));

        lblFechaNacimiento.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblFechaNacimiento.setForeground(new java.awt.Color(37, 37, 37));

        lblTelefono.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblTelefono.setForeground(new java.awt.Color(37, 37, 37));

        lblRfc.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblRfc.setForeground(new java.awt.Color(37, 37, 37));

        lblDiscapacitado.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblDiscapacitado.setForeground(new java.awt.Color(37, 37, 37));

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

        jSeparator2.setForeground(new java.awt.Color(106, 27, 49));

        jLabel12.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(37, 37, 37));
        jLabel12.setText("Vigencia:");

        comboTarifa.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        comboTarifa.setForeground(new java.awt.Color(37, 37, 37));
        comboTarifa.setEnabled(false);
        comboTarifa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTarifaActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(37, 37, 37));
        jLabel10.setText("Costo:");

        lblCosto.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblCosto.setForeground(new java.awt.Color(37, 37, 37));

        javax.swing.GroupLayout pnlCamposLayout = new javax.swing.GroupLayout(pnlCampos);
        pnlCampos.setLayout(pnlCamposLayout);
        pnlCamposLayout.setHorizontalGroup(
            pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCamposLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCamposLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFechaNacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(pnlCamposLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDiscapacitado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(pnlCamposLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblRfc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(pnlCamposLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(pnlCamposLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(pnlCamposLayout.createSequentialGroup()
                        .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2)
                            .addComponent(jSeparator1)
                            .addGroup(pnlCamposLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCurp, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(pnlCamposLayout.createSequentialGroup()
                        .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlCamposLayout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboTarifa, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(pnlCamposLayout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblCosto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(6, 6, 6))))
        );
        pnlCamposLayout.setVerticalGroup(
            pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCamposLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCurp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lblNombre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(lblFechaNacimiento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lblTelefono))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblRfc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(lblDiscapacitado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(comboTarifa)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(lblCosto))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        btnConfirmar.setBackground(new java.awt.Color(106, 27, 49));
        btnConfirmar.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnConfirmar.setForeground(new java.awt.Color(242, 242, 242));
        btnConfirmar.setText("Confirmar");
        btnConfirmar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConfirmar.setEnabled(false);
        btnConfirmar.setPreferredSize(new java.awt.Dimension(120, 40));
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlCampos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        pnlContenidoLayout.setVerticalGroup(
            pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenidoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCampos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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
     * Método que reacciona al evento de dar clic en el botón para confirmar la
     * licencia y culminar el trámite.
     *
     * @param evt Evento del mouse al que se escucha.
     */
    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        try {
            // Se valida que el solicitante cumpla con los requisitos.
            registroLicencia.validarRequisitos(persona);

            TarifaLicenciaDTO tarifa = (TarifaLicenciaDTO) comboTarifa.getSelectedItem();
            // Generamos una licencia.
            LicenciaDTO licencia = registroLicencia.generarLicencia(persona, tarifa);

            // Se manda a agregar la licencia.
            // Mandamos sólo la curp para luego obtener la entidad de la persona
            // para asociarla a la licencia.
            registroLicencia.agregarLicencia(persona.getCurp(), licencia);

            // Se redirecciona a la pantalla de trámite completado donde están
            // los datos de la licencia y del solicitante.
            FrmLicenciaRecibo frmLicenciaRecibo = new FrmLicenciaRecibo(persona, licencia);
            frmLicenciaRecibo.setVisible(true);
            this.dispose();
        } catch (NegocioException ne) {
            // Se manda un mensaje de que se interrumpió el proceso por incumplimiento
            // de requisitos.
            JOptionPane.showMessageDialog(this, ne.getMessage(), "¡Error!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnConfirmarActionPerformed

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
     * persona por su CURP.
     *
     * @param evt Evento del mouse al que se escucha.
     */
    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        try {
            // Se obtiene la CURP ingresada.
            String curp = txtCurp.getText().trim().toUpperCase();

            // Creamos una instancia de validadores.
            Validadores v = new Validadores();

            // Validamos la CURP.
            v.validarCurp(curp);

            // Se busca si hay alguna persona registrada con la CURP ingresada.
            persona = registroLicencia.buscarPersonaCurp(curp);

            // Se habilitan el botón de confirmar y el combobox de vigencia.
            btnConfirmar.setEnabled(true);
            comboTarifa.setEnabled(true);

            // Se muestran los datos de la persona encontrada.
            mostrarDatosPersona();
        } catch (PresentacionException | NegocioException ex) {
            // Se muestra un mensaje si la CURP fue mal ingresada o si no se
            // encontró una persona con dicha CURP.
            JOptionPane.showMessageDialog(this, ex.getMessage(), "¡Oops!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    /**
     * Método que reacciona al evento de seleccionar una tarifa.
     *
     * @param evt Evento del mouse al que se escucha.
     */
    private void comboTarifaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTarifaActionPerformed
        // Calculamos el costo de la licencia.
        calcularCosto();
    }//GEN-LAST:event_comboTarifaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JPanel btnCerrar;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JPanel btnMinimizar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JComboBox<dtos.TarifaLicenciaDTO> comboTarifa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblCosto;
    private javax.swing.JLabel lblDiscapacitado;
    private javax.swing.JLabel lblFechaNacimiento;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblRfc;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JPanel pnlBarra;
    private javax.swing.JPanel pnlCampos;
    private javax.swing.JPanel pnlContenido;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JTextField txtCurp;
    // End of variables declaration//GEN-END:variables

}
