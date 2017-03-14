package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto varastoAlkusaldolla;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        varastoAlkusaldolla = new Varasto(10, 5);
    }

    @Test
    public void josUudenVarastonAlkusaldoOnAnnettuSeAsetetaanOikeinKonstruktorissa() {
        assertEquals(5, varastoAlkusaldolla.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaAlkusaldollisellaVarastollaOikeaTilavuus() {
        assertEquals(10, varastoAlkusaldolla.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void josAlkusaldoOnSamaKuinVarastonTilavuusAsetusOnnistuu() {
        Varasto hetiTaysiVarasto = new Varasto(10, 10);
        assertEquals(10, hetiTaysiVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void josAlkusaldoOnSuurempiKuinVarastonTilavuusAsetusOnnistuu() {
        Varasto hetiLiianPieniVarasto = new Varasto(10, 15);
        assertEquals(10, hetiLiianPieniVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void josUudenAlkusaldollisenVarastonTilavuudeksiOnAnnettuNegatiivinenSeOnNolla() {
        Varasto miinusvarastoAlkusaldolla = new Varasto(-3, 5);
        assertEquals(0, miinusvarastoAlkusaldolla.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void varastolleEiVoiAntaaNegatiivistaAlkusaldoa() {
        Varasto miinusvarasto = new Varasto(10, -5);
        assertEquals(0, miinusvarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void josUudenAlkusaldollisenVarastonTilavuudeksiOnAnnettuNollaSeOnNolla() {
        Varasto nollavarasto = new Varasto(0, 3);
        assertEquals(0, nollavarasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void josUudenVarastonTilavuudeksiOnAnnettuNollaSeOnNolla() {
        Varasto nollavarasto = new Varasto(0);
        assertEquals(0, nollavarasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisayksenJalkeenSaldoEiVoiYlittaaVarastonTilavuutta() {
        varasto.lisaaVarastoon(7);
        varasto.lisaaVarastoon(5);
        assertEquals(varasto.getSaldo(), 10, vertailuTarkkuus);
    }

    @Test
    public void eiVoiLisataNegatiivistaMaaraa() {
        varasto.lisaaVarastoon(7);
        varasto.lisaaVarastoon(-10);
        assertEquals(varasto.getSaldo(), 7, vertailuTarkkuus);
    }

    // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);
        double saatuMaara = varasto.otaVarastosta(2);
        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);
        varasto.otaVarastosta(2);
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void eiVoiOttaaNegatiivistaMaaraa() {
        varasto.lisaaVarastoon(4);
        varasto.otaVarastosta(-3);
        assertEquals(varasto.getSaldo(), 4, vertailuTarkkuus);
    }

    @Test
    public void josYrittaaOttaaNegatiivisenMaaranPalauttaaOtetuksiMaaraksiNollan() {
        assertTrue(varasto.otaVarastosta(-2) == 0);
    }

    @Test
    public void josOtetaanEnemmanKuinOnVarastossaSaadaanKuitenkinKaikkiMitaVarastossaOn() {
        varasto.lisaaVarastoon(8);
        assertTrue(varasto.otaVarastosta(9) == 8);
    }

    @Test
    public void josOtetaanEnemmanKuinOnVarastossaSaldoEiMeneNegatiiviseksi() {
        varasto.lisaaVarastoon(8);
        varasto.otaVarastosta(9);
        assertEquals(varasto.getSaldo(), 0, vertailuTarkkuus);
    }

    @Test
    public void tekstimuotoinenEsitysOikein() {
        varasto.lisaaVarastoon(2.5);
        String teksti = "saldo = 2.5, vielä tilaa 7.5";
        assertEquals(varasto.toString(), teksti);
    }

}
