package presentacion;

import dtos.AutomovilDTO;
import dtos.LicenciaDTO;
import dtos.PersonaDTO;
import dtos.PlacasDTO;
import dtos.TarifaLicenciaDTO;
import dtos.TarifaPlacasDTO;
import excepciones.NegocioException;
import excepciones.PresentacionException;
import java.awt.Color;
import java.awt.Frame;
import javax.swing.JOptionPane;
import negocio.IRegistroPlacasBO;
import negocio.RegistroPlacasBO;
import utilidades.FormatoDinero;
import utilidades.Paleta;
import utilidades.Validadores;

/**
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public class FrmPlacasAutoNuevo extends javax.swing.JFrame {
    
    private IRegistroPlacasBO registroPlacas;
    private PersonaDTO persona;
    private LicenciaDTO licencia;
    int mouseX, mouseY;
        
    /** Creates new form FrmPlacasDatosAuto */
    public FrmPlacasAutoNuevo(PersonaDTO persona, LicenciaDTO licencia) {
        initComponents();
        this.registroPlacas = new RegistroPlacasBO();
        this.persona = persona;
        this.licencia = licencia;
        cargarCosto();
    }
    
    private void cargarCosto() {
        // Creamos una instancia del formateador de dinero.
        FormatoDinero fd = new FormatoDinero();

        // Obtenemos el costo del trámite de placas para un auto nuevo.
        TarifaPlacasDTO tarifa = registroPlacas.buscarTarifa("Automóvil nuevo");
        String costo = fd.formatear(tarifa.getCosto());
        
        // Se muestra el costo.
        lblCosto.setText(costo);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlBarra = new javax.swing.JPanel();
        pnlHeader = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        btnMinimizar = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        pnlTitulo = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        pnlContenido = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        pnlCampos = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtNumSerie = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtMarca = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtLinea = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtColor = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtModelo = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        lblCosto = new javax.swing.JLabel();
        btnVolver = new javax.swing.JButton();
        btnConfirmar = new javax.swing.JButton();

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

        jLabel7.setBackground(new java.awt.Color(242, 242, 242));
        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(242, 242, 242));
        jLabel7.setText("Sistema de tránsito");
        pnlHeader.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

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

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("-");

        javax.swing.GroupLayout btnMinimizarLayout = new javax.swing.GroupLayout(btnMinimizar);
        btnMinimizar.setLayout(btnMinimizarLayout);
        btnMinimizarLayout.setHorizontalGroup(
            btnMinimizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnMinimizarLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        btnMinimizarLayout.setVerticalGroup(
            btnMinimizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnMinimizarLayout.createSequentialGroup()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, Short.MAX_VALUE)
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

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("X");

        javax.swing.GroupLayout btnCerrarLayout = new javax.swing.GroupLayout(btnCerrar);
        btnCerrar.setLayout(btnCerrarLayout);
        btnCerrarLayout.setHorizontalGroup(
            btnCerrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );
        btnCerrarLayout.setVerticalGroup(
            btnCerrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        pnlHeader.add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 0, -1, 30));

        pnlTitulo.setBackground(new java.awt.Color(106, 27, 49));
        pnlTitulo.setPreferredSize(new java.awt.Dimension(700, 125));
        pnlTitulo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo.png"))); // NOI18N
        pnlTitulo.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 25, -1, -1));

        jLabel12.setFont(new java.awt.Font("SansSerif", 0, 48)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(188, 149, 92));
        jLabel12.setText("Sistema de tránsito");
        pnlTitulo.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(262, 25, -1, 100));

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

        jLabel13.setFont(new java.awt.Font("SansSerif", 0, 40)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Trámite de placas");

        pnlCampos.setBackground(new java.awt.Color(242, 242, 242));

        jLabel14.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(37, 37, 37));
        jLabel14.setText("Número de serie:");

        txtNumSerie.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        txtNumSerie.setForeground(new java.awt.Color(37, 37, 37));

        jLabel15.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(37, 37, 37));
        jLabel15.setText("Marca:");

        txtMarca.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        txtMarca.setForeground(new java.awt.Color(37, 37, 37));

        jLabel18.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(37, 37, 37));
        jLabel18.setText("Línea:");

        txtLinea.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        txtLinea.setForeground(new java.awt.Color(37, 37, 37));

        jLabel20.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(37, 37, 37));
        jLabel20.setText("Color:");

        txtColor.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        txtColor.setForeground(new java.awt.Color(37, 37, 37));

        jLabel21.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(37, 37, 37));
        jLabel21.setText("Modelo:");

        txtModelo.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        txtModelo.setForeground(new java.awt.Color(37, 37, 37));

        jSeparator1.setForeground(new java.awt.Color(106, 27, 49));

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel2.setText("Costo:");

        lblCosto.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N

        javax.swing.GroupLayout pnlCamposLayout = new javax.swing.GroupLayout(pnlCampos);
        pnlCampos.setLayout(pnlCamposLayout);
        pnlCamposLayout.setHorizontalGroup(
            pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCamposLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(pnlCamposLayout.createSequentialGroup()
                        .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel14)
                            .addComponent(jLabel18)
                            .addComponent(jLabel21)
                            .addComponent(txtModelo, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                            .addComponent(txtLinea)
                            .addComponent(txtNumSerie))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                        .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel15)
                            .addComponent(jLabel20)
                            .addComponent(txtMarca, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                            .addComponent(txtColor)))
                    .addGroup(pnlCamposLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCosto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlCamposLayout.setVerticalGroup(
            pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCamposLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNumSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLinea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblCosto))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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

        btnConfirmar.setBackground(new java.awt.Color(106, 27, 49));
        btnConfirmar.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnConfirmar.setForeground(new java.awt.Color(242, 242, 242));
        btnConfirmar.setText("Confirmar");
        btnConfirmar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConfirmar.setPreferredSize(new java.awt.Dimension(120, 40));
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlContenidoLayout = new javax.swing.GroupLayout(pnlContenido);
        pnlContenido.setLayout(pnlContenidoLayout);
        pnlContenidoLayout.setHorizontalGroup(
            pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenidoLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlContenidoLayout.createSequentialGroup()
                        .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlCampos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(70, Short.MAX_VALUE))
            .addGroup(pnlContenidoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlContenidoLayout.setVerticalGroup(
            pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenidoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addGap(30, 30, 30)
                .addComponent(pnlCampos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
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
     * @param evt Evento del mouse al que se escucha.
     */
    private void btnMinimizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizarMouseClicked
        this.setState(Frame.ICONIFIED);
    }//GEN-LAST:event_btnMinimizarMouseClicked

    /**
     * Método que cambia el color del botón para minimizar la ventana.
     * @param evt Evento del mouse al que se escucha.
     */
    private void btnMinimizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizarMouseEntered
        btnMinimizar.setBackground(Paleta.VERDE);
    }//GEN-LAST:event_btnMinimizarMouseEntered

    /**
     * Método que cambia el color del botón para minimizar la ventana a su color
     * original.
     * @param evt Evento del mouse al que se escucha.
     */
    private void btnMinimizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizarMouseExited
        btnMinimizar.setBackground(new Color(88, 88, 88));
    }//GEN-LAST:event_btnMinimizarMouseExited

    /**
     * Método que reacciona al evento de dar clic en el botón para cerrar la
     * ventana.
     * @param evt Evento del mouse al que se escucha.
     */
    private void btnCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseClicked
        System.exit(0);
    }//GEN-LAST:event_btnCerrarMouseClicked

    /**
     * Método que cambia el color del botón para cerrar la ventana.
     * @param evt Evento del mouse al que se escucha.
     */
    private void btnCerrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseEntered
        btnCerrar.setBackground(Color.red);
    }//GEN-LAST:event_btnCerrarMouseEntered

    /**
     * Método que cambia el color del botón para cerrar la ventana a su color
     * original.
     * @param evt Evento del mouse al que se escucha.
     */
    private void btnCerrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseExited
        btnCerrar.setBackground(new Color(88, 88, 88));
    }//GEN-LAST:event_btnCerrarMouseExited

    /**
     * Método que mueve la ventana cuando se arrastra el mouse por el header.
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
     * @param evt Evento del mouse al que se escucha.
     */
    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        FrmPlacasSeleccionVehiculo frmPlacasSeleccion = new FrmPlacasSeleccionVehiculo(persona, licencia);
        frmPlacasSeleccion.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnVolverActionPerformed

    /**
     * Método que reacciona al evento de dar clic en el botón para confirmar los
     * datos del nuevo vehículo y culminar el trámite.
     * @param evt Evento del mouse al que se escucha.
     */
    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        AutomovilDTO automovil = null;
        try {
            // Obtenemos los datos que ingresó el usuario.
            String numSerie = txtNumSerie.getText().trim().toUpperCase();
            String marca = txtMarca.getText().trim().toUpperCase();
            String linea = txtLinea.getText().trim().toUpperCase();
            String color = txtColor.getText().trim().toUpperCase();
            String modelo = txtModelo.getText().strip();
            
            // Creamos una instancia para validar que se hayan ingresado correctamente
            // los datos del vehículo.
            Validadores v = new Validadores();
            // Validamos cada dato.
            v.validarNumSerie(numSerie);
            v.validarMarca(marca);
            v.validarLinea(linea);
            v.validarColor(color);
            v.validarModelo(modelo);
            
            automovil = new AutomovilDTO(numSerie, marca, linea, color, modelo);
            
            PlacasDTO placaDTO = registroPlacas.generarPlaca("Automóvil nuevo");
            
            automovil.setNumPlaca(placaDTO.getNumero());
            
            registroPlacas.agregarPlacaNuevo(automovil, persona.getCurp(), placaDTO);
            
            FrmPlacasRecibo frmPlacasRecibo = new FrmPlacasRecibo(persona, placaDTO);
            frmPlacasRecibo.setVisible(true);
            this.dispose();
        } catch (PresentacionException pe) {
            // Se manda un mensaje de que se introdujeron datos erróneos.
            JOptionPane.showMessageDialog(this, pe.getMessage(), "¡Oops!", JOptionPane.WARNING_MESSAGE);
        } catch (NegocioException ne) {
            // Se manda un mensaje de ya está registrado el vehículo.
            int opcion = JOptionPane.showConfirmDialog(this, ne.getMessage() + "\n¿Desea continuar con el trámite?", "¡Error!", JOptionPane.YES_NO_OPTION);
            if (opcion == 0) {
                FrmPlacasAutoUsado frmPlacasAutoUsado = new FrmPlacasAutoUsado(persona, automovil, null);
                frmPlacasAutoUsado.setVisible(true);
            } else {
                FrmHome frmHome = new FrmHome();
                frmHome.setVisible(true);
            }
            this.dispose();
        }
    }//GEN-LAST:event_btnConfirmarActionPerformed


    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnCerrar;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JPanel btnMinimizar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblCosto;
    private javax.swing.JPanel pnlBarra;
    private javax.swing.JPanel pnlCampos;
    private javax.swing.JPanel pnlContenido;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JTextField txtColor;
    private javax.swing.JTextField txtLinea;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtModelo;
    private javax.swing.JTextField txtNumSerie;
    // End of variables declaration//GEN-END:variables

}
