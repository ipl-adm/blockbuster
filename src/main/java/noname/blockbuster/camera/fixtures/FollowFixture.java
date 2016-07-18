package noname.blockbuster.camera.fixtures;

import net.minecraft.command.CommandException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import noname.blockbuster.camera.Position;

/**
 * Follow camera fixture
 *
 * This camera fixture is responsible for following given entity from specified
 * angle and relative calculated position.
 */
public class FollowFixture extends LookFixture
{
    public FollowFixture(long duration, Position position, Entity entity)
    {
        super(duration, position, entity);
        this.calculateRelativePosition();
    }

    @Override
    public void edit(String[] args, EntityPlayer player) throws CommandException
    {
        super.edit(args, player);

        if (this.entity != null)
        {
            this.calculateRelativePosition();
        }
    }

    /**
     * Following method recalculates relative position from stored entity.
     */
    private void calculateRelativePosition()
    {
        float x = (float) (this.position.point.x - this.entity.posX);
        float y = (float) (this.position.point.y - this.entity.posY);
        float z = (float) (this.position.point.z - this.entity.posZ);

        this.position.point.set(x, y, z);
    }

    @Override
    public void applyFixture(float progress, Position pos)
    {
        float x = (float) (this.entity.posX + this.position.point.x);
        float y = (float) (this.entity.posY + this.position.point.y);
        float z = (float) (this.entity.posZ + this.position.point.z);

        pos.copy(this.position);
        pos.point.set(x, y, z);
    }
}
