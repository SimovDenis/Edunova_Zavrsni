/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package zavrsni.view;

import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.TimePickerSettings;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import zavrsni.controller.ObradaDjelatnik;
import zavrsni.controller.ObradaKupac;
import zavrsni.controller.ObradaRacun;
import zavrsni.controller.ObradaStavka;
import zavrsni.model.Djelatnik;
import zavrsni.model.Kupac;
import zavrsni.model.Racun;
import zavrsni.model.Stavka;
import zavrsni.util.Alati;
import zavrsni.util.MotoException;

/**
 *
 * @author Denis
 */
public class ProzorRacun extends javax.swing.JFrame implements MotoViewSucelje {

    private ObradaRacun obrada;
    private ObradaStavka obradaStavka;

    /**
     * Creates new form ProzorRacun
     */
    public ProzorRacun() {
        initComponents();
        obrada = new ObradaRacun();
        obradaStavka = new ObradaStavka();
        setTitle(Alati.NAZIV_APP + " | RACUNI");
        lblOper.setText(Alati.getOperater());
        ucitajKupca();
        ucitajDjelatnika();
        definirajVrijemeKupovine();
        ucitaj();
    }

    public ObradaRacun getObradaRacun() {
        return obrada;
    }

    private void ucitajKupca() {
        DefaultComboBoxModel<Kupac> m = new DefaultComboBoxModel<>();

        Kupac d = new Kupac();
        d.setSifra(0);
        d.setIme("Odaberite ");
        d.setPrezime("kupca");
        m.addElement(d);

        m.addAll(new ObradaKupac().read());

        cmbKupac.setModel(m);
        cmbKupac.repaint();
    }

    private void definirajVrijemeKupovine() {
        DatePickerSettings dps = new DatePickerSettings(Locale.of("hr", "HR"));
        dps.setFormatForDatesCommonEra("dd. MM. YYYY.");
        dps.setTranslationClear("Očisti");
        dps.setTranslationToday("Danas");
        dtpVrijemeKupovine.datePicker.setSettings(dps);

        TimePickerSettings tps = dtpVrijemeKupovine.timePicker.getSettings();

        tps.setFormatForDisplayTime("HH:mm");
        tps.use24HourClockFormat();

        ArrayList<LocalTime> lista = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 60; j += 10) {
                lista.add(LocalTime.of(i, j));
            }
        }

        tps.generatePotentialMenuTimes(lista);

    }

    private void ucitajDjelatnika() {
        DefaultComboBoxModel<Djelatnik> m = new DefaultComboBoxModel<>();

        Djelatnik d = new Djelatnik();
        d.setSifra(0);
        d.setIme("djelatnika");
        d.setPrezime("Odaberite ");
        m.addElement(d);

        m.addAll(new ObradaDjelatnik().read());

        cmbDjelatnik.setModel(m);
        cmbDjelatnik.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        lstPodaci = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        txtUvjet = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtNacinPlacanja = new javax.swing.JTextField();
        btnDodaj = new javax.swing.JButton();
        btnPromjeni = new javax.swing.JButton();
        btnObriši = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        lblOper = new javax.swing.JLabel();
        dtpVrijemeKupovine = new com.github.lgooddatepicker.components.DateTimePicker();
        jLabel2 = new javax.swing.JLabel();
        cmbDjelatnik = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnPretraziKupca = new javax.swing.JButton();
        cmbKupac = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstProizvodiNaRacunu = new javax.swing.JList<>();
        lblProizvodiNaRacunu = new javax.swing.JLabel();
        btnTrazi = new javax.swing.JButton();
        btnDodajStavku = new javax.swing.JButton();
        lblRacuni = new javax.swing.JLabel();
        txtBrojRacuna = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnObrišiStavku = new javax.swing.JButton();
        lblUkupanIznos = new javax.swing.JLabel();
        btnRacun = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lstPodaci.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstPodaciValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lstPodaci);

        jLabel1.setText("Broj računa");

        txtUvjet.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUvjetKeyPressed(evt);
            }
        });

        jLabel5.setText("Način plaćanja");

        btnDodaj.setText("Dodaj");
        btnDodaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDodajActionPerformed(evt);
            }
        });

        btnPromjeni.setText("Promjeni");
        btnPromjeni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPromjeniActionPerformed(evt);
            }
        });

        btnObriši.setText("Obriši");
        btnObriši.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObrišiActionPerformed(evt);
            }
        });

        jToolBar1.setRollover(true);
        jToolBar1.add(lblOper);

        jLabel2.setText("Datum i vrijeme kupovine");

        jLabel3.setText("Djelatnik");

        jLabel4.setText("Odaberi ili pretraži kupca");

        btnPretraziKupca.setText("Pretraži kupca");
        btnPretraziKupca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPretraziKupcaActionPerformed(evt);
            }
        });

        lstProizvodiNaRacunu.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(lstProizvodiNaRacunu);

        lblProizvodiNaRacunu.setText("Proizvodi na odabranom računu");

        btnTrazi.setText("Pretraži račune");
        btnTrazi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTraziActionPerformed(evt);
            }
        });

        btnDodajStavku.setText("Dodaj na račun");
        btnDodajStavku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDodajStavkuActionPerformed(evt);
            }
        });

        lblRacuni.setText("Prikazani su svi računi");

        jLabel7.setText("Pretraga računa po broju računa");

        btnObrišiStavku.setText("Obriši s računa");
        btnObrišiStavku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObrišiStavkuActionPerformed(evt);
            }
        });

        lblUkupanIznos.setText("Ukupan iznos:");

        btnRacun.setText("Račun");
        btnRacun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRacunActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRacuni, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtBrojRacuna, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUvjet, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNacinPlacanja, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dtpVrijemeKupovine, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbDjelatnik, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnPretraziKupca, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbKupac, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(btnDodaj, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnPromjeni, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnObriši, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnTrazi)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 40, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(btnDodajStavku, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnObrišiStavku, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(btnRacun, javax.swing.GroupLayout.Alignment.TRAILING)))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                        .addComponent(lblProizvodiNaRacunu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lblUkupanIznos, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblProizvodiNaRacunu)
                            .addComponent(lblRacuni))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addComponent(jScrollPane1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtUvjet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTrazi))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBrojRacuna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbKupac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDodajStavku, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPretraziKupca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbDjelatnik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnObrišiStavku))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dtpVrijemeKupovine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNacinPlacanja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnObriši)
                            .addComponent(btnPromjeni)
                            .addComponent(btnDodaj)
                            .addComponent(btnRacun))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUkupanIznos))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lstPodaciValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstPodaciValueChanged
        if (evt.getValueIsAdjusting() || lstPodaci.getSelectedValue() == null) {
            return;
        }

        obrada.setEntitet(lstPodaci.getSelectedValue());
        popuniView();
    }//GEN-LAST:event_lstPodaciValueChanged

    private void btnDodajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDodajActionPerformed
        obrada.setEntitet(new Racun());
        popuniModel();

        try {
            obrada.create();
            obrada.refresh();
            ucitaj();
        } catch (MotoException ex) {
            JOptionPane.showMessageDialog(getRootPane(), ex.getMessage());
        }
    }//GEN-LAST:event_btnDodajActionPerformed

    private void btnObrišiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObrišiActionPerformed
        if (lstPodaci.getSelectedValue() == null) {
            return;
        }

        var e = lstPodaci.getSelectedValue();

        if (JOptionPane.showConfirmDialog(getRootPane(), e, "Sigurno obrisati?",
                JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
            return;
        }

        obrada.setEntitet(e);

        try {
            obrada.delete();
            ucitaj();
        } catch (MotoException ex) {
            JOptionPane.showMessageDialog(getRootPane(), ex.getPoruka());
        }
    }//GEN-LAST:event_btnObrišiActionPerformed

    private void btnPromjeniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPromjeniActionPerformed
        if (lstPodaci.getSelectedValue() == null) {
            return;
        }

        var e = lstPodaci.getSelectedValue();

        obrada.setEntitet(e);
        popuniModel();

        try {
            obrada.update();
            ucitaj();
        } catch (MotoException ex) {
            JOptionPane.showMessageDialog(getRootPane(), ex.getMessage());
            obrada.refresh();
        }
    }//GEN-LAST:event_btnPromjeniActionPerformed

    private void btnPretraziKupcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPretraziKupcaActionPerformed
        if (lstPodaci.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(getRootPane(), "Obavezno odabrati račun te ga preurediti po želji");
            return;
        }

        new ProzorOdabirKupca(this).setVisible(true);
    }//GEN-LAST:event_btnPretraziKupcaActionPerformed

    private void btnTraziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTraziActionPerformed
        DefaultListModel<Racun> m = new DefaultListModel<>();
        m.addAll(obrada.read(txtUvjet.getText()));
        lstPodaci.setModel(m);
        lstPodaci.repaint();

        lblRacuni.setText("Prikazani su rezultati pretraživanja");
    }//GEN-LAST:event_btnTraziActionPerformed

    private void txtUvjetKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUvjetKeyPressed
        btnTraziActionPerformed(null);
    }//GEN-LAST:event_txtUvjetKeyPressed

    private void btnObrišiStavkuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObrišiStavkuActionPerformed
        if (lstProizvodiNaRacunu.getSelectedValue() == null) {
            return;
        }

        DefaultListModel<Stavka> m = (DefaultListModel<Stavka>) lstProizvodiNaRacunu.getModel();
        m.removeElementAt(lstProizvodiNaRacunu.getSelectedIndex());

        List<Stavka> stavke = new ArrayList<>();
        for (int i = 0; i < m.size(); i++) {
            stavke.add(m.get(i));
        }

        obrada.getEntitet().setStavka(stavke);

        try {
            obrada.update();
        } catch (MotoException ex) {
        }

        popuniView();
        lstProizvodiNaRacunu.repaint();

        btnTraziActionPerformed(null);

    }//GEN-LAST:event_btnObrišiStavkuActionPerformed

    private void btnDodajStavkuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDodajStavkuActionPerformed
        if (lstPodaci.getSelectedValue() == null) {
            return;
        }

        new ProzorOdabirStavke(this).setVisible(true);

    }//GEN-LAST:event_btnDodajStavkuActionPerformed

    private void btnRacunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRacunActionPerformed
        BigDecimal zbroj = new BigDecimal(0);
        if (lstPodaci.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(rootPane, "Prvo odaberite račun za ispis!");
            return;
        }
        new File("./racuni").mkdir();
        Racun n = lstPodaci.getSelectedValue();
        Document document = new Document() {
        };
        try {
            File file = new File("./racuni/" + "Broj_Računa " + n.getBrojRacuna() + ".pdf");
            PdfWriter.getInstance(document, new FileOutputStream(file));
        } catch (FileNotFoundException | DocumentException ex) {

        }
        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        try {
            document.addTitle("MOTO TRGOVINA - Route 66");
            document.add(new Paragraph("Broj računa: " + n.getBrojRacuna(), font));
            document.add(new Paragraph("Vrijeme kupovine: " + n.getVrijemeKupovine(), font));
            document.add(new Paragraph("Djelatnik: " + n.getDjelatnik(), font));
            document.add(new Paragraph("Kupac: " + n.getKupac(), font));
            document.add(new Paragraph("________________________________________"));
            for (int i = 0; i < n.getStavka().size(); i++) {

                document.add(new Paragraph("" + n.getStavka().get(i) + " "
                        + n.getStavka().get(i).getProizvod().getCijena() + "€", font));
                zbroj = zbroj.add(n.getStavka().get(i).getProizvod().getCijena()
                        .multiply(new BigDecimal(n.getStavka().get(i).getKolicina())));
            }
            document.add(new Paragraph("________________________________________"));

            document.add(new Paragraph("Ukupno za platiti: " + zbroj + "€", font));

        } catch (DocumentException ex) {
        }
        document.close();
        try {
            Desktop.getDesktop().open(new File("racuni"));
        } catch (IOException ex) {
        }


    }//GEN-LAST:event_btnRacunActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDodaj;
    private javax.swing.JButton btnDodajStavku;
    private javax.swing.JButton btnObriši;
    private javax.swing.JButton btnObrišiStavku;
    private javax.swing.JButton btnPretraziKupca;
    private javax.swing.JButton btnPromjeni;
    private javax.swing.JButton btnRacun;
    private javax.swing.JButton btnTrazi;
    private javax.swing.JComboBox<Djelatnik> cmbDjelatnik;
    private javax.swing.JComboBox<Kupac> cmbKupac;
    private com.github.lgooddatepicker.components.DateTimePicker dtpVrijemeKupovine;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblOper;
    private javax.swing.JLabel lblProizvodiNaRacunu;
    private javax.swing.JLabel lblRacuni;
    private javax.swing.JLabel lblUkupanIznos;
    private javax.swing.JList<Racun> lstPodaci;
    private javax.swing.JList<Stavka> lstProizvodiNaRacunu;
    private javax.swing.JTextField txtBrojRacuna;
    private javax.swing.JTextField txtNacinPlacanja;
    private javax.swing.JTextField txtUvjet;
    // End of variables declaration//GEN-END:variables

    @Override
    public void ucitaj() {
        DefaultListModel<Racun> m = new DefaultListModel<>();
        m.addAll(obrada.read());
        lstPodaci.setModel(m);
        lstPodaci.repaint();
    }

    @Override
    public void popuniModel() {
        var e = obrada.getEntitet();

        e.setBrojRacuna(txtBrojRacuna.getText());
        e.setKupac((Kupac) cmbKupac.getSelectedItem());
        e.setDjelatnik((Djelatnik) cmbDjelatnik.getSelectedItem());

        LocalDate ld = dtpVrijemeKupovine.datePicker.getDate();
        LocalTime lt = dtpVrijemeKupovine.timePicker.getTime();

        try {
            LocalDateTime ldt = LocalDateTime.of(ld, lt);
            e.setVrijemeKupovine(Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant()));
        } catch (Exception ex) {
        }

        e.setNacinPlacanja(txtNacinPlacanja.getText());

    }

    @Override
    public void popuniView() {
        var e = obrada.getEntitet();

        txtBrojRacuna.setText(e.getBrojRacuna());

        if (e.getKupac() == null) {
            cmbKupac.setSelectedIndex(0);
        } else {
            cmbKupac.setSelectedItem(e.getKupac());
        }

        if (e.getDjelatnik() == null) {
            cmbDjelatnik.setSelectedIndex(0);
        } else {
            cmbDjelatnik.setSelectedItem(e.getDjelatnik());
        }

        if (e.getVrijemeKupovine() == null) {
            dtpVrijemeKupovine.datePicker.setDate(null);
            dtpVrijemeKupovine.timePicker.setTime(null);
        } else {
            LocalDate ld = e.getVrijemeKupovine().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            dtpVrijemeKupovine.datePicker.setDate(ld);

            LocalTime lt = e.getVrijemeKupovine().toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
            dtpVrijemeKupovine.timePicker.setTime(lt);
        }

        txtNacinPlacanja.setText(e.getNacinPlacanja());

        DefaultListModel<Stavka> m = new DefaultListModel<>();
        m.addAll(e.getStavka());
        lstProizvodiNaRacunu.setModel(m);
        lstProizvodiNaRacunu.repaint();

        lblProizvodiNaRacunu.setText("Proizvodi na računu (" + e.getStavka().size() + ")");

        BigDecimal ukupno = new BigDecimal(0);

        for (int i = 0; i < m.size(); i++) {
            ukupno = ukupno.add(obrada.getEntitet().getStavka().get(i).getProizvod().getCijena()
                    .multiply(new BigDecimal(obrada.getEntitet().getStavka().get(i).getKolicina())));
        }

        lblUkupanIznos.setText("Ukupan iznos: " + ukupno);

    }
}
