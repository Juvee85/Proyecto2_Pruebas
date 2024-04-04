package presentacion;

import dtos.ReporteDTO;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public class FrmReportesResultados extends javax.swing.JFrame {
    
    int mouseX, mouseY;
    
    /** Creates new form FrmReportesResultados */
    public FrmReportesResultados(String nombre, String tipo, String fechaInicio, String fechaFin) {
        initComponents();
        
        if(!nombre.isBlank()) {
            lblNombre.setText("Nombre: " + nombre);
        }
        
        if(!tipo.isBlank()) {
            lblTipo.setText("Tipo: " + tipo);
        }
        
        if(!fechaInicio.isBlank() || !fechaFin.isBlank()) {
            lblPeriodo.setText("Periodo: ");
            if(!fechaInicio.isBlank()) {
                lblPeriodo.setText(lblPeriodo.getText() + fechaInicio + " -");
            }
            if(!fechaFin.isBlank()) {
                lblPeriodo.setText(lblPeriodo.getText() + "- " + fechaFin);
            }
        }
        
        formatearTabla();
        cargarDatos();
    }
    
    public void formatearTabla() {
        tblTramites.getTableHeader().setBackground(new Color(106, 27, 49));
        tblTramites.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 20));
        tblTramites.getTableHeader().setForeground(new Color(188, 149, 92));
    }
    
    public void cargarDatos() {
        List<ReporteDTO> lista = new ArrayList<>();
        lista.add(new ReporteDTO("Diego Valenzuela Dávila", "Placas", "$1,000.00", "12/08/2023 09:19:48"));
        lista.add(new ReporteDTO("Diego Valenzuela Lira", "Licencia", "$200.00", "30/10/2023 10:20:33"));
        lista.add(new ReporteDTO("Diego Valenzuela Lira", "Placas", "$1,000.00", "30/10/2023 11:35:16"));
        lista.add(new ReporteDTO("Diego Valenzuela Urías", "Placas", "$1,000.00", "01/11/2023 16:07:14"));
        lista.add(new ReporteDTO("Diego Valenzuela Urías", "Placas", "$1,500.00", "08/02/2024 15:49:52"));
        lista.add(new ReporteDTO("Diego Valenzuela Parra", "Licencia", "$1,100.00", "15/03/2024 14:50:21"));
        lista.add(new ReporteDTO("Diego Valenzuela Parra", "Placas", "$1,500.00", "17/03/2024 13:32:01"));
        
        llenarTablaReporte(lista);
    }
    
    private void llenarTablaReporte(List<ReporteDTO> lista) {
        DefaultTableModel modeloTabla = (DefaultTableModel) tblTramites.getModel();

        if (modeloTabla.getRowCount() > 0) {
            for (int i = modeloTabla.getRowCount() - 1; i > -1; i--) {
                modeloTabla.removeRow(i);
            }
        }

        if (lista != null) {
            lista.forEach(row -> {
                Object[] fila = new Object[4];
                fila[0] = row.getNombre();
                fila[1] = row.getTipo();
                fila[2] = row.getCosto();
                fila[3] = row.getFechaRealizacion();

                modeloTabla.addRow(fila);
            });
        }
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
        lblNombre = new javax.swing.JLabel();
        lblTipo = new javax.swing.JLabel();
        lblPeriodo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTramites = new javax.swing.JTable();
        btnVolver = new javax.swing.JButton();
        btnAnterior = new javax.swing.JButton();
        btnSiguiente = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        btnVolver1 = new javax.swing.JButton();

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

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("-");
        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));

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

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("X");
        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));

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

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Reportes");
        jLabel7.setFont(new java.awt.Font("SansSerif", 0, 40)); // NOI18N

        pnlCampos.setBackground(new java.awt.Color(242, 242, 242));

        jLabel8.setText("Filtros:");
        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(37, 37, 37));

        lblNombre.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(37, 37, 37));

        lblTipo.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        lblTipo.setForeground(new java.awt.Color(37, 37, 37));

        lblPeriodo.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        lblPeriodo.setForeground(new java.awt.Color(37, 37, 37));

        javax.swing.GroupLayout pnlCamposLayout = new javax.swing.GroupLayout(pnlCampos);
        pnlCampos.setLayout(pnlCamposLayout);
        pnlCamposLayout.setHorizontalGroup(
            pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCamposLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlCamposLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lblTipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPeriodo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlCamposLayout.setVerticalGroup(
            pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCamposLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTipo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPeriodo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblTramites.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nombre", "Tipo", "Costo", "Fecha de realización"
            }
        ));
        tblTramites.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblTramites.setBackground(new java.awt.Color(11, 35, 30));
        tblTramites.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblTramites.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        tblTramites.setForeground(new java.awt.Color(242, 242, 242));
        tblTramites.setName(""); // NOI18N
        tblTramites.setSelectionBackground(new java.awt.Color(106, 27, 49));
        tblTramites.setSelectionForeground(new java.awt.Color(242, 242, 242));
        tblTramites.setShowGrid(true);
        tblTramites.setSurrendersFocusOnKeystroke(true);
        jScrollPane1.setViewportView(tblTramites);

        btnVolver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/atras.png"))); // NOI18N
        btnVolver.setText("Volver");
        btnVolver.setBackground(new java.awt.Color(11, 35, 30));
        btnVolver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVolver.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnVolver.setForeground(new java.awt.Color(242, 242, 242));
        btnVolver.setPreferredSize(new java.awt.Dimension(120, 40));
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        btnAnterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/siguiente.png"))); // NOI18N
        btnAnterior.setText("Siguiente");
        btnAnterior.setBackground(new java.awt.Color(188, 149, 92));
        btnAnterior.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAnterior.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnAnterior.setForeground(new java.awt.Color(242, 242, 242));
        btnAnterior.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnAnterior.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        btnAnterior.setPreferredSize(new java.awt.Dimension(120, 40));
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });

        btnSiguiente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/anterior.png"))); // NOI18N
        btnSiguiente.setText("Anterior");
        btnSiguiente.setBackground(new java.awt.Color(188, 149, 92));
        btnSiguiente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSiguiente.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnSiguiente.setForeground(new java.awt.Color(242, 242, 242));
        btnSiguiente.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnSiguiente.setPreferredSize(new java.awt.Dimension(120, 40));
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Página 1");
        jLabel12.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btnVolver1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pdf.png"))); // NOI18N
        btnVolver1.setText("Exportar");
        btnVolver1.setBackground(new java.awt.Color(106, 27, 49));
        btnVolver1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVolver1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnVolver1.setForeground(new java.awt.Color(242, 242, 242));
        btnVolver1.setPreferredSize(new java.awt.Dimension(120, 40));
        btnVolver1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolver1ActionPerformed(evt);
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                        .addComponent(btnSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAnterior, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(btnVolver1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(pnlCampos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        pnlContenidoLayout.setVerticalGroup(
            pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenidoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCampos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVolver1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        getContentPane().add(pnlContenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 700, 450));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnMinimizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizarMouseClicked
        this.setState(Frame.ICONIFIED);
    }//GEN-LAST:event_btnMinimizarMouseClicked

    private void btnMinimizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizarMouseEntered
        btnMinimizar.setBackground(Color.red);
    }//GEN-LAST:event_btnMinimizarMouseEntered

    private void btnMinimizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizarMouseExited
        btnMinimizar.setBackground(new Color(88, 88, 88));
    }//GEN-LAST:event_btnMinimizarMouseExited

    private void btnCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseClicked
        System.exit(0);
    }//GEN-LAST:event_btnCerrarMouseClicked

    private void btnCerrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseEntered
        btnCerrar.setBackground(Color.red);
    }//GEN-LAST:event_btnCerrarMouseEntered

    private void btnCerrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseExited
        btnCerrar.setBackground(new Color(88, 88, 88));
    }//GEN-LAST:event_btnCerrarMouseExited

    private void pnlHeaderMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlHeaderMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - mouseX, y - mouseY);
    }//GEN-LAST:event_pnlHeaderMouseDragged

    private void pnlHeaderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlHeaderMousePressed
        mouseX = evt.getX();
        mouseY = evt.getY();
    }//GEN-LAST:event_pnlHeaderMousePressed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        FrmReportesFiltros frmReportesFiltros = new FrmReportesFiltros();
        frmReportesFiltros.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAnteriorActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void btnVolver1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolver1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVolver1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnterior;
    private javax.swing.JPanel btnCerrar;
    private javax.swing.JPanel btnMinimizar;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JButton btnVolver;
    private javax.swing.JButton btnVolver1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPeriodo;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JPanel pnlBarra;
    private javax.swing.JPanel pnlCampos;
    private javax.swing.JPanel pnlContenido;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JTable tblTramites;
    // End of variables declaration//GEN-END:variables

}
