package fms.business.form;

import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;


public class PaperFormTest {

    protected Scan makeScan() {
        return new Scan();
    }

    @Test
    public void testIsSigned() {
        PaperForm form = new PaperForm();
        assertFalse(form.isSigned());

        Date signedAt = new Date();
        form.setSignedAt(signedAt);
        assertEquals(signedAt, form.getSignedAt());

        assertTrue(form.isSigned());
    }

    @Test
    public void testAddingAndRemovingScans() {
        int numberOfScans = 4;
        Scan scanA = makeScan();
        Scan scanB = makeScan();
        Scan scanC = makeScan();
        Scan scanD = makeScan();


        PaperForm form = new PaperForm();
        form.addScan(scanA);
        form.addScan(scanB);
        form.addScan(scanC);
        form.addScan(scanD);

        List<Scan> fields = form.getScans();
        assertEquals(numberOfScans, fields.size());


        //Remove and add
        form.removeScan(scanA);
        assertEquals(--numberOfScans, fields.size());

        form.addScan(scanA);
        assertEquals(++numberOfScans, fields.size());

        //Remove all
        form.removeScan(scanA);
        assertEquals(--numberOfScans, fields.size());

        form.removeScan(scanB);
        assertEquals(--numberOfScans, fields.size());

        form.removeScan(scanC);
        assertEquals(--numberOfScans, fields.size());

        form.removeScan(scanD);
        assertEquals(--numberOfScans, fields.size());
    }

}