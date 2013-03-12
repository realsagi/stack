
package stack;

import java.awt.Toolkit;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Stack;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
public class StackView extends FrameView {
    private String infixstack="";
    private String postfixstack="";
    private double polroum=0;

    public StackView(SingleFrameApplication app) {
        
        super(app);

        initComponents();

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = StackApp.getApplication().getMainFrame();
            aboutBox = new StackAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        StackApp.getApplication().show(aboutBox);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Infix = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        PostFix = new javax.swing.JTextArea();
        Button1 = new javax.swing.JButton();
        Button2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        inputinfix = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        inputpostfix = new javax.swing.JTextField();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(stack.StackApp.class).getContext().getResourceMap(StackView.class);
        mainPanel.setBackground(resourceMap.getColor("mainPanel.background")); // NOI18N
        mainPanel.setForeground(resourceMap.getColor("mainPanel.foreground")); // NOI18N
        mainPanel.setName("mainPanel"); // NOI18N

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        Infix.setBackground(resourceMap.getColor("Infix.background")); // NOI18N
        Infix.setColumns(20);
        Infix.setFont(resourceMap.getFont("Infix.font")); // NOI18N
        Infix.setRows(5);
        Infix.setText(resourceMap.getString("Infix.text")); // NOI18N
        Infix.setName("Infix"); // NOI18N
        Infix.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                InfixMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Infix);

        jLabel2.setFont(resourceMap.getFont("jLabel2.font")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        PostFix.setBackground(resourceMap.getColor("PostFix.background")); // NOI18N
        PostFix.setColumns(20);
        PostFix.setFont(resourceMap.getFont("PostFix.font")); // NOI18N
        PostFix.setRows(5);
        PostFix.setText(resourceMap.getString("PostFix.text")); // NOI18N
        PostFix.setName("PostFix"); // NOI18N
        PostFix.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PostFixMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(PostFix);

        Button1.setBackground(resourceMap.getColor("Button1.background")); // NOI18N
        Button1.setFont(resourceMap.getFont("Button1.font")); // NOI18N
        Button1.setText(resourceMap.getString("Button1.text")); // NOI18N
        Button1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Button1.setName("Button1"); // NOI18N
        Button1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Button1MouseClicked(evt);
            }
        });

        Button2.setBackground(resourceMap.getColor("Button2.background")); // NOI18N
        Button2.setFont(resourceMap.getFont("Button2.font")); // NOI18N
        Button2.setForeground(resourceMap.getColor("Button2.foreground")); // NOI18N
        Button2.setText(resourceMap.getString("Button2.text")); // NOI18N
        Button2.setName("Button2"); // NOI18N
        Button2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Button2MouseClicked(evt);
            }
        });

        jButton1.setFont(resourceMap.getFont("jButton1.font")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jButton2.setFont(resourceMap.getFont("jButton2.font")); // NOI18N
        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        inputinfix.setFont(resourceMap.getFont("inputinfix.font")); // NOI18N
        inputinfix.setText(resourceMap.getString("inputinfix.text")); // NOI18N
        inputinfix.setName("inputinfix"); // NOI18N
        inputinfix.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inputinfixMouseClicked(evt);
            }
        });

        jLabel3.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        inputpostfix.setFont(resourceMap.getFont("inputpostfix.font")); // NOI18N
        inputpostfix.setText(resourceMap.getString("inputpostfix.text")); // NOI18N
        inputpostfix.setName("inputpostfix"); // NOI18N
        inputpostfix.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inputpostfixMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inputinfix, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(Button2, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Button1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inputpostfix, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(154, 154, 154)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 656, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(162, 162, 162))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(46, 46, 46)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(inputpostfix, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(inputinfix, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(Button1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Button2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE))
                        .addContainerGap())))
        );

        menuBar.setName("menuBar"); // NOI18N

        helpMenu.setForeground(resourceMap.getColor("helpMenu.foreground")); // NOI18N
        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setFont(resourceMap.getFont("helpMenu.font")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(stack.StackApp.class).getContext().getActionMap(StackView.class, this);
        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setFont(resourceMap.getFont("aboutMenuItem.font")); // NOI18N
        aboutMenuItem.setText(resourceMap.getString("aboutMenuItem.text")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 1275, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1105, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

private void InfixMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InfixMouseClicked

    if (evt.getButton() == MouseEvent.BUTTON1) { 
        //the first button (left?) 
    } 
    else if (evt.getButton() == MouseEvent.BUTTON2) { 
        //the second button (center?) 
    } 
    else if (evt.getButton() == MouseEvent.BUTTON3) { 
    Toolkit.getDefaultToolkit().getSystemEventQueue().push( new TCPopupEventQueue());
  }
     
}//GEN-LAST:event_InfixMouseClicked

private void PostFixMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PostFixMouseClicked
    if (evt.getButton() == MouseEvent.BUTTON1) { 
        //the first button (left?) 
    } 
    else if (evt.getButton() == MouseEvent.BUTTON2) { 
        //the second button (center?) 
    } 
    else if (evt.getButton() == MouseEvent.BUTTON3) { 
        Toolkit.getDefaultToolkit().getSystemEventQueue().push( new TCPopupEventQueue());
    }
}//GEN-LAST:event_PostFixMouseClicked

private void Button1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Button1MouseClicked
   if (evt.getButton() == MouseEvent.BUTTON1) { 
    PostFix.setText("---------------------------------------------------------------------------\n");
    PostFix.setText(PostFix.getText()+"Step\tSymbol\tStack\tOutput\n");
    PostFix.setText(PostFix.getText()+"---------------------------------------------------------------------------\n");
    checkinfix();
   }
}//GEN-LAST:event_Button1MouseClicked

public void checkinfix(){
    char[]  c = inputinfix.getText().toCharArray();
    int check = 0;
    int summ=0;
    int checkoperator = 0;
    int checkstring = 0;
    for(int j=0;j<c.length;j++){
        if((c[j]>='a' && c[j]<='z') || (c[j]>='A' && c[j]<='Z') || (c[j]=='+'  || c[j]=='-'  || c[j]=='*'  || c[j]=='/'  || c[j]=='(' || c[j]==')' || c[j]=='^')){
            if((c[j]=='+'  || c[j]=='-'  || c[j]=='*'  || c[j]=='/'  || c[j]=='(' || c[j]==')' || c[j]=='^')){
                checkoperator++;
            }
            if((c[j]>='a' && c[j]<='z') || (c[j]>='A' && c[j]<='Z')){
                for(int i=c.length-1;i>=0;i--){
                    if(c[j]==c[i]){
                        summ++;
                    }
                }
                checkstring++;
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"ข้อมูล  \""+c[j]+"\"  ไม่ถูกต้อง");
            check++;
            break;
        }
    }
    if(checkoperator==0 && check ==0){
        JOptionPane.showMessageDialog(null,"ข้อมูล Infix ไม่มี โอเปอร์เรเตอร์");
    }
    else if(checkstring==0 && check ==0){
        JOptionPane.showMessageDialog(null,"ข้อมูล infix ไม่มี ตัวแปร");
    }
    else if(checkstring<summ){
        JOptionPane.showMessageDialog(null,"ตัวแปรของคุณซ้ำกัน");
    }
    else if (check==0){
        stack();
    }
}

public void stack(){
    char[]  c = inputinfix.getText().toCharArray();
    String[] stack = new String[inputinfix.getText().length()];
    String[] output = new String[inputinfix.getText().length()];
    try{
    for(int j=0;j<c.length;j++){
        if((c[j]=='+'  || c[j]=='-'  || c[j]=='*'  || c[j]=='/'  || c[j]=='(' || c[j]==')' || c[j]=='^')){
            if(j==0){
                stack[j]=Character.toString(c[j]);
                output[j]="";
            }
            else{
                stack[j]=stack[j-1]+Character.toString(c[j]);
                if(c[j]<'a' || c[j]>'z' || c[j]<'A' || c[j]>'Z'){
                    output[j]=output[j-1];
                }
                char[] laststack = new char[stack[j-1].length()];
                laststack = stack[j-1].toCharArray();
                for(int k=laststack.length-1;k>=0;k--){
                    if(c[j]==laststack[k] && laststack[k]!='(' && laststack[k]!=')'){
                        stack[j]=stack[j-1].substring(0,k)+c[j];
                        output[j]=output[j-1]+invert(stack[j-1].substring(k));
                    }
                    if(c[j]=='('){
                        stack[j]=stack[j-1]+Character.toString(c[j]);
                    }
                    else if(c[j]=='^'){
                        stack[j]=stack[j-1]+Character.toString(c[j]);
                    }
                    else if(c[j]==')' && laststack[k]!='('){
                        stack[j]=stack[j-1].substring(0,k-1);
                        output[j]=output[j-1]+invert(stack[j-1].substring(k));
                    }
                    else if(c[j]=='*' && laststack[k]=='^' || laststack[k]=='/'){
                       stack[j]=stack[j-1].substring(0,k)+c[j];
                       output[j]=output[j-1]+invert(stack[j-1].substring(k));
                    }
                    else if(c[j]=='/' && (laststack[k]=='*' || laststack[k]=='^')){
                       stack[j]=stack[j-1].substring(0,k)+c[j]; 
                       output[j]=output[j-1]+invert(stack[j-1].substring(k));
                    }
                    else if(c[j]=='+' && (laststack[k]=='*' || laststack[k]=='^' || laststack[k]=='/' || laststack[k]=='-')){
                       stack[j]=stack[j-1].substring(0,k)+c[j]; 
                       output[j]=output[j-1]+invert(stack[j-1].substring(k));
                    }
                    else if(c[j]=='-' && (laststack[k]=='*' || laststack[k]=='^' || laststack[k]=='/' || laststack[k]=='+')){
                       stack[j]=stack[j-1].substring(0,k)+c[j]; 
                       output[j]=output[j-1]+invert(stack[j-1].substring(k));
                    }
                    else{
                        break;
                    }
                }
            }
        }
        else{
            if(j==0){
                stack[j]="";
                output[j]=Character.toString(c[j]);
            }
            else{
                stack[j]=stack[j-1];
                output[j]=output[j-1]+Character.toString(c[j]);
            }
        }
    }
    }catch(Exception e){JOptionPane.showMessageDialog(null,"โปรแกรมทำงานผิดพลาด  "+e);}
    for(int i=0;i<stack.length;i++){
        PostFix.setText(PostFix.getText()+(i+1)+"\t"+c[i]+"\t"+stack[i]+"\t"+output[i]+"\n");
    }
    PostFix.setText(PostFix.getText()+(stack.length+1)+"\t\t\t"+output[output.length-1]+""+invert(stack[stack.length-1])+"\n");
    PostFix.setText(PostFix.getText()+"---------------------------------------------------------------------------\n");
    PostFix.setText(PostFix.getText()+"ผลลัพธ์ของพิพจน์แบบ Postfix คือ "+output[output.length-1]+""+invert(stack[stack.length-1])+"\n");
    inputpostfix.setText(output[output.length-1]+invert(stack[stack.length-1]));
    postfixstack=output[output.length-1]+invert(stack[stack.length-1]);
}

public String invert(String word){
     String temp = "";
        for (int i = word.length() - 1; i >= 0; i--)
            temp += word.charAt(i);
        return temp;
}

private void Button2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Button2MouseClicked
    if (evt.getButton() == MouseEvent.BUTTON1) { 
    Infix.setText("");
    PostFix.setText("");
    polroum=0;
    infixstack="";
    postfixstack="";
    }
}//GEN-LAST:event_Button2MouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        if (evt.getButton() == MouseEvent.BUTTON1) { 
            Infix.setText("---------------------------------------------------------------------------\n");
            Infix.setText(Infix.getText()+"Step\tSymbol\tStack\n");
            Infix.setText(Infix.getText()+"---------------------------------------------------------------------------\n");
            checkpostfix();
        }
    }//GEN-LAST:event_jButton1MouseClicked

    private void inputinfixMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inputinfixMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON3) { 
            Toolkit.getDefaultToolkit().getSystemEventQueue().push( new TCPopupEventQueue());
        }
    }//GEN-LAST:event_inputinfixMouseClicked

    private void inputpostfixMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inputpostfixMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON3) { 
            Toolkit.getDefaultToolkit().getSystemEventQueue().push( new TCPopupEventQueue());
        }
    }//GEN-LAST:event_inputpostfixMouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        if (evt.getButton() == MouseEvent.BUTTON1) { 
            checkx();
            Infix.setText(Infix.getText()+"ผลรวมของผลลัพธ์ที่ได้ คือ "+polroum+"\n");
            PostFix.setText(PostFix.getText()+"ผลรวมของผลลัพธ์ที่ได้คือ "+polroum+"\n");
        }
    }//GEN-LAST:event_jButton2MouseClicked
public void checkx(){
        char[] c = infixstack.toCharArray();
        String aa = infixstack;
        String bb = postfixstack;
        int check=0;
        int Int=0;
        for(int i=0;i<c.length;i++){
        try{
            if((c[i]>='a' && c[i]<='z') || (c[i]>='A' && c[i]<='Z')){
                String input = (String) JOptionPane.showInputDialog(new JFrame(),"ป้อนค่าให้กับตัวแปร "+c[i],"ป้อนข้อมูล", JOptionPane.INFORMATION_MESSAGE,new ImageIcon("java2sLogo.GIF"), null, "ป้อนเฉพาะตัวเลข");
                int a = Integer.parseInt(input);
                Infix.setText(Infix.getText().replace(Character.toString(c[i]),Integer.toString(a)));
                PostFix.setText(PostFix.getText().replace(Character.toString(c[i]),Integer.toString(a)));
                aa=aa.replace(Character.toString(c[i]),"["+Integer.toString(a)+"]");
                bb=bb.replace(Character.toString(c[i]),"["+Integer.toString(a)+"]");
                Int++;
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"ป้อนข้อมูลเฉพาะตัวเลขเท่านั้น");
            break;
        }
        check++;       
        }
        double[] sum = new double[Int];
        int k=0;
        int l=0;
        String x = "";
        if(check==c.length){
            System.out.println("BB = "+bb);
            for(int i=0;i<bb.length();i++){
                String tt = Character.toString(bb.charAt(i));
                if (!tt.equals("+") && !tt.equals("-") && !tt.equals("*")  && !tt.equals("/")  && !tt.equals("^")){
                    if(tt.equals("[")){
                        continue;
                    }
                    else if(!tt.equals("[") && !tt.equals("]")){
                        x=x+tt;
                    }
                    else if(tt.equals("]")) {
                        sum[k]=Integer.parseInt(x);
                        x="";
                        System.out.println("sum["+k+"] = "+sum[k]);
                        k++;
                    }
                }
                else{
                    double a=0;  double b=0;  double re=0;
                    if(tt.equals("+")){
                        b=sum[k-1];
                        System.out.print("B = "+b);
                        a=sum[k-2];
                        System.out.print("A = "+a);
                        re=a+b;
                        System.out.print("Re = "+re);
                        sum[k-2]=re;
                        System.out.print("sum["+(k-2)+"] = "+sum[k-2]);
                        k=k-1;
                        System.out.println("K = "+k);
                    }
                    if(tt.equals("-")){
                        b=sum[k-1];
                        System.out.print("B = "+b);
                        a=sum[k-2];
                        System.out.print("A = "+a);
                        re=a-b;
                        System.out.print("Re = "+re);
                        sum[k-2]=re;
                        System.out.print("sum["+(k-2)+"] = "+sum[k-2]);
                        k=k-1;
                        System.out.println("K = "+k);
                    }
                    if(tt.equals("*")){
                        b=sum[k-1];
                        System.out.print("B = "+b);
                        a=sum[k-2];
                        System.out.print("A = "+a);
                        re=a*b;
                        System.out.print("Re = "+re);
                        sum[k-2]=re;
                        System.out.print("sum["+(k-2)+"] = "+sum[k-2]);
                        k=k-1;
                        System.out.println("K = "+k);
                    }
                    if(tt.equals("/")){
                        b=sum[k-1];
                        System.out.print("B = "+b);
                        a=sum[k-2];
                        System.out.print("A = "+a);
                        re=a/b;
                        System.out.print("Re = "+re);
                        sum[k-2]=re;
                        System.out.print("sum["+(k-2)+"] = "+sum[k-2]);
                        k=k-1;
                        System.out.println("K = "+k);
                    }
                    if(tt.equals("^")){
                        b=sum[k-1];
                        System.out.print("B = "+b);
                        a=sum[k-2];
                        double q=sum[k-2];
                        System.out.print("A = "+a);
                        for(int ww=1;ww<b;ww++){
                            q=q*a;
                        }
                        re=q;
                        System.out.print("Re = "+re);
                        sum[k-2]=re;
                        System.out.print("sum["+(k-2)+"] = "+sum[k-2]);
                        k=k-1;
                        System.out.println("K = "+k);
                    }
                }
            }
            JOptionPane.showMessageDialog(null,"แทนค่าตัวแปรได้ข้อมูลดังนี้\n"+aa+" = "+sum[k-1]);
            polroum=sum[k-1];
        }
}
public void checkpostfix(){
    char[]  c = inputpostfix.getText().toCharArray();
    int check = 0;
    int summ=0;
    int checkoperator = 0;
    int checkstring = 0;
    for(int j=0;j<c.length;j++){
        if((c[j]>='a' && c[j]<='z') || (c[j]>='A' && c[j]<='Z') || (c[j]=='+'  || c[j]=='-'  || c[j]=='*'  || c[j]=='/'  || c[j]=='(' || c[j]==')' || c[j]=='^')){
            if((c[j]=='+'  || c[j]=='-'  || c[j]=='*'  || c[j]=='/'  || c[j]=='(' || c[j]==')' || c[j]=='^')){
                checkoperator++;
            }
            if((c[j]>='a' && c[j]<='z') || (c[j]>='A' && c[j]<='Z')){
                for(int i=c.length-1;i>=0;i--){
                    if(c[j]==c[i]){
                        summ++;
                    }
                }
                checkstring++;
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"ข้อมูล  \""+c[j]+"\"  ไม่ถูกต้อง");
            check++;
            break;
        }
    }
    if(checkoperator==0 && check ==0){
        JOptionPane.showMessageDialog(null,"ข้อมูล Postfix ไม่มี โอเปอร์เรเตอร์");
    }
    else if(checkstring==0 && check ==0){
        JOptionPane.showMessageDialog(null,"ข้อมูล Postfix ไม่มี ตัวแปร");
    }
    else if(checkstring<summ){
        JOptionPane.showMessageDialog(null,"ตัวแปรของคุณซ้ำกัน");
    }
    else if (check==0){
        stackpostfix();
    }
}

public void stackpostfix(){
    String[] ss = new String[inputpostfix.getText().length()];
    String str = inputpostfix.getText();
    Stack stack = new Stack();
    for (int i = 0; i < str.length(); i++){
        char c = str.charAt(i); 
        if (c == '+' || c == '-' || c == '*' || c == '/' || c=='^'){
            String b = (String)stack.pop();
            String a = (String)stack.pop();
            stack.push("(" + a + c + b +")");
            ss[i]=ss[i-1].substring(0,ss[i-1].indexOf(a))+"(" + a + c + b +")";
        }
        else{
            stack.push(String.valueOf(c));
            if(i==0){
                ss[i]=Character.toString(c);
            }
            else{
            ss[i]=ss[i-1]+c;
            }            
        }
    }
    String stringstack=(String)stack.pop();
    inputinfix.setText(stringstack);
    char[] c = inputpostfix.getText().toCharArray();
    
    for(int i=0;i<c.length;i++){
        Infix.setText(Infix.getText()+(i+1)+"\t"+c[i]+"\t"+ss[i]+"\n");      
    }
    Infix.setText(Infix.getText()+"---------------------------------------------------------------------------\n");
    Infix.setText(Infix.getText()+"ผลลัพธ์ของพิพจน์แบบ Infix คือ "+stringstack+"\n");
    infixstack=stringstack;

}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Button1;
    private javax.swing.JButton Button2;
    private javax.swing.JTextArea Infix;
    private javax.swing.JTextArea PostFix;
    private javax.swing.JTextField inputinfix;
    private javax.swing.JTextField inputpostfix;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;
}
