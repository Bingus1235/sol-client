package me.mcblueparrot.client.ui.screen.mods;

import java.awt.Button;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.imageio.ImageIO;

import com.google.gson.JsonParser;

import gg.essential.elementa.ElementaVersion;
import gg.essential.elementa.UIComponent;
import gg.essential.elementa.WindowScreen;
import gg.essential.elementa.components.ScrollComponent;
import gg.essential.elementa.components.UIBlock;
import gg.essential.elementa.components.UIContainer;
import gg.essential.elementa.components.UIImage;
import gg.essential.elementa.components.UIRoundedRectangle;
import gg.essential.elementa.components.UIText;
import gg.essential.elementa.components.input.UITextInput;
import gg.essential.elementa.components.inspector.Inspector;
import gg.essential.elementa.constraints.CenterConstraint;
import gg.essential.elementa.constraints.ChildBasedSizeConstraint;
import gg.essential.elementa.constraints.ColorConstraint;
import gg.essential.elementa.constraints.ConstantColorConstraint;
import gg.essential.elementa.constraints.ConstraintType;
import gg.essential.elementa.constraints.PixelConstraint;
import gg.essential.elementa.constraints.RelativeConstraint;
import gg.essential.elementa.constraints.SiblingConstraint;
import gg.essential.elementa.constraints.SubtractiveConstraint;
import gg.essential.elementa.constraints.animation.AnimatingConstraints;
import gg.essential.elementa.constraints.animation.Animations;
import gg.essential.elementa.constraints.resolution.ConstraintVisitor;
import gg.essential.elementa.effects.ScissorEffect;
import gg.essential.elementa.font.BasicFontRenderer;
import gg.essential.elementa.font.FontProvider;
import gg.essential.elementa.font.FontRenderer;
import gg.essential.elementa.font.data.Font;
import gg.essential.elementa.font.data.FontInfo;
import gg.essential.elementa.markdown.MarkdownComponent;
import gg.essential.universal.UMatrixStack;
import gg.essential.universal.UKeyboard.Modifiers;
import me.mcblueparrot.client.Client;
import me.mcblueparrot.client.mod.Mod;
import me.mcblueparrot.client.mod.ModCategory;
import me.mcblueparrot.client.mod.impl.SolClientMod;
import me.mcblueparrot.client.util.Utils;
import me.mcblueparrot.client.util.data.Colour;
import me.mcblueparrot.client.util.font.SlickFontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class ModsScreen extends AbstractModsScreen {

	private List<ModButton> buttons = new ArrayList<>();

	public ModsScreen(GuiScreen parent, Mod mod) {
		this();
	}

	public ModsScreen(GuiScreen parent) {
		this();
	}

	public ModsScreen() {
		super(Minecraft.getMinecraft().currentScreen);

		new UIText("Mods", false).setChildOf(getWindow()).setX(new CenterConstraint()).setY(new PixelConstraint(10F));

		for(ModCategory category : ModCategory.values()) {
			if(category == ModCategory.ALL) continue;

			if(category.toString() != null) {
				new UIText(category.toString(), false).setChildOf(container).setX(new CenterConstraint())
						.setY(new SiblingConstraint(10F));

			}

			for(Mod mod : category.getMods("")) {
				buttons.add((ModButton) new ModButton(this, mod).setChildOf(container).setX(new CenterConstraint()).setY(new SiblingConstraint(5F))
						.setWidth(new PixelConstraint(300F)).setHeight(new PixelConstraint(30F)));
			}
		}

//		new Inspector(getWindow()).setX(new PixelConstraint(5)).setY(new PixelConstraint(5)).setChildOf(getWindow());
	}

	@Override
	public void onKeyPressed(int keyCode, char typedChar, Modifiers modifiers) {
		super.onKeyPressed(keyCode, typedChar, modifiers);

		if(keyCode == Client.INSTANCE.modsKey.getKeyCode()) {
			restorePreviousScreen();
		}
	}

	public void updateFont() {

	}

	public void switchMod(Mod mod) {
		// TODO Auto-generated method stub

	}

	@Override
	public void initScreen(int width, int height) {
		super.initScreen(width, height);

		try {
			BufferedImage cogImage = ImageIO.read(getClass().getResourceAsStream("/assets/minecraft/textures/gui/sol_client_settings_" + Utils.getTextureScale() + ".png"));

			for(ModButton button : buttons) {
				button.init(cogImage);
			}
		}
		catch(IOException error) {
		}
	}

}
