package me.sashie.skriptyaml.skript;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.sashie.skriptyaml.SkriptYaml;

@Name("Unload YAML")
@Description("Unloads a YAML file from memory.")
@Examples({
		"unload yaml \"config\""
})
@Since("1.0.0")
public class EffUnloadYaml extends Effect {

	static {
		Skript.registerEffect(EffUnloadYaml.class, "unload [y[a]ml] %string%");
	}

	private Expression<String> file;

	@Override
	protected void execute(@Nullable Event event) {
		final String name = this.file.getSingle(event);

		if (!SkriptYaml.YAML_STORE.containsKey(name)) {
			//SkriptYaml.warn("No yaml file by the name '" + name + "' has been loaded");
			return;
		}

		SkriptYaml.YAML_STORE.remove(name);
	}

	@Override
	public String toString(@Nullable Event event, boolean b) {
		return "unload yaml " + this.file.toString(event, b);
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
		file = (Expression<String>) exprs[0];
		return true;
	}
}
