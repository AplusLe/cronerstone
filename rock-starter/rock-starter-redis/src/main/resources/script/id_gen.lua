local partitionCount = 4096;
local step = 1;
local startStep = 0;

local tag = KEYS[1];
local partition
if KEYS[2] == nil then
  partition = 0;
else
  partition = KEYS[2] % partitionCount;
end

local now = redis.call('TIME');

local milliSecondKey =  tag ..'_' .. partition .. '_' .. now[1] .. '_' .. math.floor(now[2]/1000);

local count;
repeat
  count = tonumber(redis.call('INCRBY', milliSecondKey, step));
  if count > (1024 - step) then
      now = redis.call('TIME');
      milliSecondKey = tag ..'_' .. partition .. '_' .. now[1] .. '_' .. math.floor(now[2]/1000);
  end
until count <= (1024 - step)

if count == step then
  redis.call('PEXPIRE', milliSecondKey, 5);
end

-- second, microSecond, partition, seq
return {tonumber(now[1]), tonumber(now[2]), partition, count + startStep}
